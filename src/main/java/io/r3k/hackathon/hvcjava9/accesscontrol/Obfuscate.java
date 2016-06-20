package io.r3k.hackathon.hvcjava9.accesscontrol;

import java.io.UnsupportedEncodingException;

public class Obfuscate {

    public static String obfuscate(String s) {
        StringBuilder buf = new StringBuilder();
        try {
            byte[] b = s.getBytes("UTF-8");

            for (int i = 0; i < b.length; i++) {
                byte b1 = b[i];
                byte b2 = b[b.length - (i + 1)];
                if (b1 < 0 || b2 < 0) {
                    int i0 = (0xff & b1) * 256 + (0xff & b2);
                    String x = Integer.toString(i0, 36).toLowerCase();
                    buf.append("U0000", 0, 5 - x.length());
                    buf.append(x);
                } else {
                    int i1 = 127 + b1 + b2;
                    int i2 = 127 + b1 - b2;
                    int i0 = i1 * 256 + i2;
                    String x = Integer.toString(i0, 36).toLowerCase();

                    int j0 = Integer.parseInt(x, 36);
                    int j1 = (i0 / 256);
                    int j2 = (i0 % 256);
                    byte bx = (byte) ((j1 + j2 - 254) / 2);

                    buf.append("000", 0, 4 - x.length());
                    buf.append(x);
                }
            }

        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }
        return buf.toString();

    }

    public static String deobfuscate(String s) {
        try {
            byte[] b = new byte[s.length() / 2];
            int l = 0;
            for (int i = 0; i < s.length(); i += 4) {
                if (s.charAt(i) == 'U') {
                    i++;
                    String x = s.substring(i, i + 4);
                    int i0 = Integer.parseInt(x, 36);
                    byte bx = (byte) (i0 >> 8);
                    b[l++] = bx;
                } else {
                    String x = s.substring(i, i + 4);
                    int i0 = Integer.parseInt(x, 36);
                    int i1 = (i0 / 256);
                    int i2 = (i0 % 256);
                    byte bx = (byte) ((i1 + i2 - 254) / 2);
                    b[l++] = bx;
                }
            }

            return new String(b, 0, l, "UTF-8");
        } catch (RuntimeException rExp) {
            return "x "+rExp.getMessage();
        } catch (UnsupportedEncodingException ex) {
            return "UnsupportedEncodingException";
        }
    }

}
