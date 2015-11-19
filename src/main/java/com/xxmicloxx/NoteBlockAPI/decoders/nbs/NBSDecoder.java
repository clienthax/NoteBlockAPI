package com.xxmicloxx.NoteBlockAPI.decoders.nbs;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class NBSDecoder {

    public static Song parse(File decodeFile) {
        try {
            return parse(new FileInputStream(decodeFile), decodeFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Song parse(InputStream inputStream) {
        return parse(inputStream, null); // Source is unknown -> no file
    }

    private static Song parse(InputStream inputStream, File decodeFile) {
        Map<Integer, Layer> layerMap = new HashMap<>();
        try {
            DataInputStream dis = new DataInputStream(inputStream);
            short length = readShort(dis);
            short songHeight = readShort(dis);
            String title = readString(dis);
            String author = readString(dis);
            readString(dis);
            String description = readString(dis);
            float speed = readShort(dis) / 100f;
            /*
            dis.readBoolean(); // auto-save
            dis.readByte(); // auto-save duration
            dis.readByte(); // x/4ths, time signature
            readInt(dis); // minutes spent on project
            readInt(dis); // left clicks (why?)
            readInt(dis); // right clicks (why?)
            readInt(dis); // blocks added
            readInt(dis); // blocks removed
            readString(dis); // .mid/.schematic file name
            */
            //Skip over the above header as its not needed here
            dis.skipBytes(23);
            dis.skipBytes(readInt(dis));

            short tick = -1;
            while (true) {
                short jumpTicks = readShort(dis); // jumps till next tick
                if (jumpTicks == 0) {
                    break;
                }
                tick += jumpTicks;
                short layer = -1;
                while (true) {
                    short jumpLayers = readShort(dis); // jumps till next layer
                    if (jumpLayers == 0) {
                        break;
                    }
                    layer += jumpLayers;
                    byte instrument = dis.readByte();
                    byte note = dis.readByte();
                    Layer l = layerMap.get((int) layer);
                    if(l == null) {
                        l = new Layer();
                        layerMap.put((int) layer, l);
                    }
                    l.setNote(tick, new Note(Instrument.values()[instrument], note));
                }
            }
            for (int i = 0; i < songHeight; i++) {
                Layer l = layerMap.get(i);
                if (l != null) {
                    l.setName(readString(dis));
                    l.setVolume(dis.readByte());
                }
            }
            return new Song(speed, layerMap, songHeight, length, title, author, description, decodeFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static short readShort(DataInputStream dis) throws IOException {
        int byte1 = dis.readUnsignedByte();
        int byte2 = dis.readUnsignedByte();
        return (short) (byte1 + (byte2 << 8));
    }

    private static int readInt(DataInputStream dis) throws IOException {
        int byte1 = dis.readUnsignedByte();
        int byte2 = dis.readUnsignedByte();
        int byte3 = dis.readUnsignedByte();
        int byte4 = dis.readUnsignedByte();
        return (byte1 + (byte2 << 8) + (byte3 << 16) + (byte4 << 24));
    }

    private static String readString(DataInputStream dis) throws IOException {
        byte[] bytes = new byte[readInt(dis)];
        dis.readFully(bytes);
        for(int i = 0; i < bytes.length; i++) {
            if(bytes[i] == 0x0D) {
                bytes[i] = ' ';
            }
        }
        return new String(bytes);
    }
}
