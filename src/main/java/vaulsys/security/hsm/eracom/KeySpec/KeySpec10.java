package vaulsys.security.hsm.eracom.KeySpec;

import vaulsys.security.hsm.eracom.base.HSMUtil;
import vaulsys.util.MyInteger;


public class KeySpec10 extends KeySpecifier {
    byte[] data;

    public KeySpec10(byte[] data) {
        format = 0x10;
        this.data = data;
    }

    public KeySpec10(byte[] response, int offset) {
        format = 0x10;
        parseByteArray(response, offset);
    }

    public byte[] getByteArray() {
        byte[] b = new byte[1 + data.length];
        b[0] = format;
        for (int i = 0; i < data.length; i++)
            b[i + 1] = data[i];

        return HSMUtil.buildVarLengthParam(b);
    }

    public int parseByteArray(byte[] response, int offset) {
        int len = HSMUtil.getLengthOfVarField(response, new MyInteger(offset));

        if (format != response[offset + 1]) {
            data = null;
            return -1;
        }

        data = new byte[8];
        for (int i = 0; i < 8; i++)
            data[i] = response[offset + 2 + i];

        return 1;
    }

    @Override
    public String toString() {
        return "Format " + HSMUtil.hexToString((new byte[]{format})) +
                "; Data " + HSMUtil.hexToString((data));
    }
}