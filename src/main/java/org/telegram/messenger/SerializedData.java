/*
 * This is the source code of Telegram for Android v. 1.3.2.
 * It is licensed under GNU GPL v. 2 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Nikolai Kudashov, 2013.
 */

package org.telegram.messenger;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
<<<<<<< HEAD

public class SerializedData extends AbsSerializedData {
=======
import java.io.IOException;

public class SerializedData {
>>>>>>> 5669c0dc333845448cc7ec627e73a6ff38979af2
    protected boolean isOut = true;
    private ByteArrayOutputStream outbuf;
    private DataOutputStream out;
    private ByteArrayInputStream inbuf;
    private DataInputStream in;
    private boolean justCalc = false;
    private int len;

    public SerializedData() {
        outbuf = new ByteArrayOutputStream();
        out = new DataOutputStream(outbuf);
    }

    public SerializedData(boolean calculate) {
        if (!calculate) {
            outbuf = new ByteArrayOutputStream();
            out = new DataOutputStream(outbuf);
        }
        justCalc = calculate;
        len = 0;
    }

    public SerializedData(int size) {
        outbuf = new ByteArrayOutputStream(size);
        out = new DataOutputStream(outbuf);
    }

<<<<<<< HEAD
    public SerializedData(byte[] data) {
=======
    public SerializedData(byte[] data){
>>>>>>> 5669c0dc333845448cc7ec627e73a6ff38979af2
        isOut = false;
        inbuf = new ByteArrayInputStream(data);
        in = new DataInputStream(inbuf);
    }

<<<<<<< HEAD
    public SerializedData(File file) throws Exception {
=======
    public SerializedData(File file) throws IOException {
>>>>>>> 5669c0dc333845448cc7ec627e73a6ff38979af2
        FileInputStream is = new FileInputStream(file);
        byte[] data = new byte[(int)file.length()];
        new DataInputStream(is).readFully(data);
        is.close();

        isOut = false;
        inbuf = new ByteArrayInputStream(data);
        in = new DataInputStream(inbuf);
    }

    public void writeInt32(int x) {
        if (!justCalc) {
            writeInt32(x, out);
        } else {
            len += 4;
        }
    }

    private void writeInt32(int x, DataOutputStream out) {
        try {
            for(int i = 0; i < 4; i++) {
                out.write(x >> (i * 8));
            }
<<<<<<< HEAD
        } catch(Exception e) {
=======
        } catch(IOException gfdsgd) {
>>>>>>> 5669c0dc333845448cc7ec627e73a6ff38979af2
            FileLog.e("tmessages", "write int32 error");
        }
    }

    public void writeInt64(long i) {
        if (!justCalc) {
            writeInt64(i, out);
        } else {
            len += 8;
        }
    }

    private void writeInt64(long x, DataOutputStream out) {
        try {
<<<<<<< HEAD
            for(int i = 0; i < 8; i++) {
                out.write((int)(x >> (i * 8)));
            }
        } catch(Exception e) {
=======
            for(int i = 0; i < 8; i++){
                out.write((int)(x >> (i * 8)));
            }
        } catch(IOException gfdsgd) {
>>>>>>> 5669c0dc333845448cc7ec627e73a6ff38979af2
            FileLog.e("tmessages", "write int64 error");
        }
    }

    public boolean readBool() {
        int consructor = readInt32();
        if (consructor == 0x997275b5) {
            return true;
        } else if (consructor == 0xbc799737) {
            return false;
        }
        FileLog.e("tmessages", "Not bool value!");
        return false;
    }

    public void writeBool(boolean value) {
        if (!justCalc) {
            if (value) {
                writeInt32(0x997275b5);
            } else {
                writeInt32(0xbc799737);
            }
        } else {
            len += 4;
        }
    }

    public int readInt32() {
        return readInt32(null);
    }

    public int readInt32(boolean[] error) {
        try {
            int i = 0;
<<<<<<< HEAD
            for(int j = 0; j < 4; j++) {
=======
            for(int j = 0; j < 4; j++){
>>>>>>> 5669c0dc333845448cc7ec627e73a6ff38979af2
                i |= (in.read() << (j * 8));
            }
            if (error != null) {
                error[0] = false;
            }
            return i;
<<<<<<< HEAD
        } catch(Exception x) {
=======
        } catch(IOException x) {
>>>>>>> 5669c0dc333845448cc7ec627e73a6ff38979af2
            if (error != null) {
                error[0] = true;
            }
            FileLog.e("tmessages", "read int32 error");
        }
        return 0;
    }

    public long readInt64() {
        return readInt64(null);
    }

    public long readInt64(boolean[] error) {
        try {
            long i = 0;
<<<<<<< HEAD
            for(int j = 0; j < 8; j++) {
=======
            for(int j = 0; j < 8; j++){
>>>>>>> 5669c0dc333845448cc7ec627e73a6ff38979af2
                i |= ((long)in.read() << (j * 8));
            }
            if (error != null) {
                error[0] = false;
            }
            return i;
<<<<<<< HEAD
        } catch (Exception x) {
=======
        } catch(IOException x) {
>>>>>>> 5669c0dc333845448cc7ec627e73a6ff38979af2
            if (error != null) {
                error[0] = true;
            }
            FileLog.e("tmessages", "read int64 error");
        }
        return 0;
    }

    public void writeRaw(byte[] b) {
        try {
            if (!justCalc) {
                out.write(b);
            } else {
                len += b.length;
            }
<<<<<<< HEAD
        } catch (Exception x) {
=======
        } catch(Exception x) {
>>>>>>> 5669c0dc333845448cc7ec627e73a6ff38979af2
            FileLog.e("tmessages", "write raw error");
        }
    }

    public void writeRaw(byte[] b, int offset, int count) {
        try {
            if (!justCalc) {
                out.write(b, offset, count);
            } else {
                len += count;
            }
<<<<<<< HEAD
        } catch (Exception x) {
=======
        } catch(Exception x) {
>>>>>>> 5669c0dc333845448cc7ec627e73a6ff38979af2
            FileLog.e("tmessages", "write raw error");
        }
    }

    public void writeByte(int i) {
        try {
            if (!justCalc) {
                out.writeByte((byte)i);
            } else {
                len += 1;
            }
        } catch (Exception e) {
            FileLog.e("tmessages", "write byte error");
        }
    }

    public void writeByte(byte b) {
        try {
            if (!justCalc) {
                out.writeByte(b);
            } else {
                len += 1;
            }
        } catch (Exception e) {
            FileLog.e("tmessages", "write byte error");
        }
    }

    public void readRaw(byte[] b) {
        try {
            in.read(b);
<<<<<<< HEAD
        } catch (Exception x) {
=======
        } catch(Exception x) {
>>>>>>> 5669c0dc333845448cc7ec627e73a6ff38979af2
            FileLog.e("tmessages", "read raw error");
        }
    }

    public byte[] readData(int count) {
        byte[] arr = new byte[count];
        readRaw(arr);
        return arr;
    }

    public String readString() {
        try {
            int sl = 1;
            int l = in.read();
<<<<<<< HEAD
            if(l >= 254) {
=======
            if(l >= 254){
>>>>>>> 5669c0dc333845448cc7ec627e73a6ff38979af2
                l = in.read() | (in.read() << 8) | (in.read() << 16);
                sl = 4;
            }
            byte[] b = new byte[l];
            in.read(b);
            int i=sl;
            while((l + i) % 4 != 0) {
                in.read();
                i++;
            }
            return new String(b, "UTF-8");
<<<<<<< HEAD
        } catch (Exception x) {
=======
        } catch(Exception x) {
>>>>>>> 5669c0dc333845448cc7ec627e73a6ff38979af2
            FileLog.e("tmessages", "read string error");
        }
        return null;
    }

    public byte[] readByteArray() {
        try {
            int sl = 1;
            int l = in.read();
<<<<<<< HEAD
            if (l >= 254) {
=======
            if (l >= 254){
>>>>>>> 5669c0dc333845448cc7ec627e73a6ff38979af2
                l = in.read() | (in.read() << 8) | (in.read() << 16);
                sl = 4;
            }
            byte[] b = new byte[l];
            in.read(b);
            int i = sl;
<<<<<<< HEAD
            while((l + i) % 4 != 0) {
=======
            while((l + i) % 4 != 0){
>>>>>>> 5669c0dc333845448cc7ec627e73a6ff38979af2
                in.read();
                i++;
            }
            return b;
<<<<<<< HEAD
        } catch (Exception x) {
=======
        } catch(Exception x) {
>>>>>>> 5669c0dc333845448cc7ec627e73a6ff38979af2
            FileLog.e("tmessages", "read byte array error");
        }
        return null;
    }

<<<<<<< HEAD
    public ByteBufferDesc readByteBuffer() {
        throw new RuntimeException("SerializedData don't support readByteBuffer");
    }

    public void writeByteArray(byte[] b) {
        try {
            if (b.length <= 253) {
=======
    public void writeByteArray(byte[] b) {
        try {
            if (b.length <= 253){
>>>>>>> 5669c0dc333845448cc7ec627e73a6ff38979af2
                if (!justCalc) {
                    out.write(b.length);
                } else {
                    len += 1;
                }
            } else {
                if (!justCalc) {
                    out.write(254);
                    out.write(b.length);
                    out.write(b.length >> 8);
                    out.write(b.length >> 16);
                } else {
                    len += 4;
                }
            }
            if (!justCalc) {
                out.write(b);
            } else {
                len += b.length;
            }
            int i = b.length <= 253 ? 1 : 4;
<<<<<<< HEAD
            while((b.length + i) % 4 != 0) {
=======
            while((b.length + i) % 4 != 0){
>>>>>>> 5669c0dc333845448cc7ec627e73a6ff38979af2
                if (!justCalc) {
                    out.write(0);
                } else {
                    len += 1;
                }
                i++;
            }
<<<<<<< HEAD
        } catch (Exception x) {
=======
        } catch(Exception x) {
>>>>>>> 5669c0dc333845448cc7ec627e73a6ff38979af2
            FileLog.e("tmessages", "write byte array error");
        }
    }

<<<<<<< HEAD
    public void writeString(String s) {
=======
    public void writeString(String s){
>>>>>>> 5669c0dc333845448cc7ec627e73a6ff38979af2
        try {
            writeByteArray(s.getBytes("UTF-8"));
        } catch(Exception x) {
            FileLog.e("tmessages", "write string error");
        }
    }

    public void writeByteArray(byte[] b, int offset, int count) {
        try {
<<<<<<< HEAD
            if(count <= 253) {
=======
            if(count <= 253){
>>>>>>> 5669c0dc333845448cc7ec627e73a6ff38979af2
                if (!justCalc) {
                    out.write(count);
                } else {
                    len += 1;
                }
            } else {
                if (!justCalc) {
                    out.write(254);
                    out.write(count);
                    out.write(count >> 8);
                    out.write(count >> 16);
                } else {
                    len += 4;
                }
            }
            if (!justCalc) {
                out.write(b, offset, count);
            } else {
                len += count;
            }
            int i = count <= 253 ? 1 : 4;
<<<<<<< HEAD
            while ((count + i) % 4 != 0) {
=======
            while ((count + i) % 4 != 0){
>>>>>>> 5669c0dc333845448cc7ec627e73a6ff38979af2
                if (!justCalc) {
                    out.write(0);
                } else {
                    len += 1;
                }
                i++;
            }
<<<<<<< HEAD
        } catch (Exception x) {
=======
        } catch(Exception x) {
>>>>>>> 5669c0dc333845448cc7ec627e73a6ff38979af2
            FileLog.e("tmessages", "write byte array error");
        }
    }

    public double readDouble() {
        try {
            return Double.longBitsToDouble(readInt64());
        } catch(Exception x) {
            FileLog.e("tmessages", "read double error");
        }
        return 0;
    }

    public void writeDouble(double d) {
        try {
            writeInt64(Double.doubleToRawLongBits(d));
        } catch(Exception x) {
            FileLog.e("tmessages", "write double error");
        }
    }

    public int length() {
        if (!justCalc) {
            return isOut ? outbuf.size() : inbuf.available();
        }
        return len;
    }

    protected void set(byte[] newData) {
        isOut = false;
        inbuf = new ByteArrayInputStream(newData);
        in = new DataInputStream(inbuf);
    }

    public byte[] toByteArray() {
        return outbuf.toByteArray();
    }
}
