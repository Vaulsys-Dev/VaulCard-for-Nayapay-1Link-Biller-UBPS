package vaulsys.protocols.ndc.encoding;

import vaulsys.util.Util;
import vaulsys.util.constants.ASCIIConstants;

import java.io.ByteArrayOutputStream;

public class VaulsysNDCConvertor extends NDCConvertor {
	protected char[] farsiCharsIndexes = {
	/* 0 */'-',
	/* 1 */'%',
	/* 2 */'ا',
	/* 3 */'ء',
	/* 4 */'ب',
	/* 5 */'پ',
	/* 6 */'ت',
	/* 7 */'ث',
	/* 8 */'ج',
	/* 9 */'چ',
	/* 10 */'ح',
	/* 11 */'خ',
	/* 12 */'د',
	/* 13 */'ذ',
	/* 14 */'ر',
	/* 15 */'ز',
	/* 16 */'ژ',
	/* 17 */'س',
	/* 18 */'ش',
	/* 19 */'ص',
	/* 20 */'ض',
	/* 21 */'0',
	/* 22 */'1',
	/* 23 */'2',
	/* 24 */'3',
	/* 25 */'4',
	/* 26 */'5',
	/* 27 */'6',
	/* 28 */'7',
	/* 29 */'8',
	/* 30 */'9',
	/* 31 */'ط',
	/* 32 */'ظ',
	/* 33 */'ع',
	/* 34 */'غ',
	/* 35 */'ف',
	/* 36 */'ق',
	/* 37 */'ک',
	/* 38 */'گ',
	/* 39 */'ل',
	/* 40 */'م',
	/* 41 */'ن',
	/* 42 */'و',
	/* 43 */'ه',
	/* 44 */'ی',
	/* 45 */' ',
	/* 46 */'.',
	/* 47 */'،',
	/* 48 */',',
	/* 49 */'آ',
	/* 50 */'ئ' };

	protected int[] farsiCharsCompleter = { -1, -1, -1, -1, /* 0-3 */
	-1, -1, -1, -1, /* 4-7 */
	-1, -1, -1, -1, /* 8-11 */
	-1, -1, -1, -1, -1, /* 12-16 */
	-1, -1, -1, -1, /* 17-20 */
	-1, -1, -1, -1, -1, /* 21-25 */
	-1, -1, -1, -1, -1, /* 26-30 */
	-1, -1, -1, -1, /* 31-34 */
	-1, -1, -1, -1, /* 35-38 */
	-1, -1, -1, -1, /* 39-42 */
	-1, -1, -1, -1, -1, -1, -1, -1 }; /* 43-48 */

	protected int[][] farsiCharsConnectingInstances = {
	/* 0 */{ 0xA4, 0xA4, 0xA4, 0xA4 },
	/* 1 */{ 0xA6, 0xA6, 0xA6, 0xA6 },
	/* 2 */{ 0x22, 0x22, 0x24, 0x24 },
	/* 3 */{ 0x25, 0x25, 0x25, 0x25 },
	/* 4 */{ 0x6B, 0x26, 0x90, 0x8C },
	/* 5 */{ 0x6C, 0x27, 0x91, 0x8D },
	/* 6 */{ 0x6D, 0x28, 0x92, 0x8E },
	/* 7 */{ 0x6E, 0x29, 0x93, 0x8F },
	/* 8 */{ 0x2B, 0x73, 0xA5, 0x6F },
	/* 9 */{ 0x2E, 0x74, 0x2F, 0x70 },
	/* 10 */{ 0x3A, 0x75, 0x3B, 0x71 },
	/* 11 */{ 0x3C, 0x76, 0x3D, 0x72 },
	/* 12 */{ 0x3E, 0x3E, 0x9D, 0x9D },
	/* 13 */{ 0x3F, 0x3F, 0x9E, 0x9E },
	/* 14 */{ 0x56, 0x56, 0x89, 0x89 }, // 0x40 is @ char!
	/* 15 */{ 0x41, 0x41, 0x8A, 0x8A },
	/* 16 */{ 0x42, 0x42, 0x8B, 0x8B },
	/* 17 */{ 0x66, 0x43, 0x83, 0x81 },
	/* 18 */{ 0x67, 0x44, 0x84, 0x82 },
	/* 19 */{ 0x68, 0x45, 0x87, 0x85 },
	/* 20 */{ 0x69, 0x46, 0x88, 0x86 },
	/* 21 */{ 0x30, 0x30, 0x30, 0x30 },
	/* 22 */{ 0x31, 0x31, 0x31, 0x31 },
	/* 23 */{ 0x32, 0x32, 0x32, 0x32 },
	/* 24 */{ 0x33, 0x33, 0x33, 0x33 },
	/* 25 */{ 0x34, 0x34, 0x34, 0x34 },
	/* 26 */{ 0x35, 0x35, 0x35, 0x35 },
	/* 27 */{ 0x36, 0x36, 0x36, 0x36 },
	/* 28 */{ 0x37, 0x37, 0x37, 0x37 },
	/* 29 */{ 0x38, 0x38, 0x38, 0x38 },
	/* 30 */{ 0x39, 0x39, 0x39, 0x39 },
	/* 31 */{ 0x47, 0x77, 0x79, 0x7B },
	/* 32 */{ 0x48, 0x78, 0x7A, 0x7C },
	/* 33 */{ 0x49, 0x4A, 0x7D, 0x7F },
	/* 34 */{ 0x4B, 0x4C, 0x7E, 0x80 },
	/* 35 */{ 0x9F, 0x4D, 0xA0, 0xA3 },
	/* 36 */{ 0x4E, 0x4F, 0xA1, 0xA2 },
	/* 37 */{ 0x50, 0x51, 0x97, 0x99 },
	/* 38 */{ 0x52, 0x53, 0x98, 0x9A },
	/* 39 */{ 0x54, 0x55, 0x9B, 0x96 },
	/* 40 */{ 0x57, 0xA8, 0x63, 0x64 },
	/* 41 */{ 0x59, 0x5A, 0x9C, 0x95 },
	/* 42 */{ 0x5B, 0x5B, 0x65, 0x65 },
	/* 43 */{ 0x5C, 0x5D, 0x61, 0x60 },
	/* 44 */{ 0x5E, 0x5F, 0x62, 0x94 },
	/* 45 */{ 0x21, 0x21, 0x21, 0x21 }, // 0x20
	/* 46 */{ 0xA8, 0xA8, 0xA8, 0xA8 },
	/* 47 */{ 0x7A, 0x7A, 0x7A, 0x7A },
	/* 48 */{ 0x2C, 0x2C, 0x2C, 0x2C },
	/* 49 */{ 0x23, 0x23, 0x23, 0x23 },
	/* 50 */{ 0x5E, 0x25, 0x62, 0x25 } // harfe vasat nadarim! 0x25
										// dovom
	};

	@Override
	public int[] getFarsiCharsCompleter() {
		return farsiCharsCompleter;
	}

	@Override
	public int[][] getFarsiCharsConnectingInstances() {
		return farsiCharsConnectingInstances;
	}

	@Override
	public char[] getFarsiCharsIndexes() {
		return farsiCharsIndexes;
	}

	@Override
	public boolean isInNumberRange(byte b) {
		return b >= (byte) 0x30 && b <= (byte) 0x39;
	}
}