import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;

class Scanner {

	private PushbackInputStream in;
	private int BUFSIZE = 1000;
	private byte[] buf = new byte[this.BUFSIZE];

	public Scanner(InputStream i) {
		this.in = new PushbackInputStream(i);
	}

	private static boolean isPeculiar(int ch) {
		return (ch == 43) || (ch == 45);
	}

	private static boolean isLetter(int ch) {
		return ((ch >= 65) && (ch <= 90)) || ((ch >= 97) && (ch <= 122));
	}

	private static boolean isSpecialInitial(int ch) {
		return (ch == 33) || (ch == 36) || (ch == 37) || (ch == 38)
				|| (ch == 42) || (ch == 47) || (ch == 58) || (ch == 60)
				|| (ch == 61) || (ch == 62) || (ch == 63) || (ch == 94)
				|| (ch == 95) || (ch == 126);
	}

	private static boolean isInitial(int ch) {
		return (isLetter(ch)) || (isSpecialInitial(ch));
	}

	private static boolean isDigit(int ch) {
		return (ch >= 48) && (ch <= 57);
	}

	private static boolean isSpecialSubsequent(int ch) {
		return (ch == 43) || (ch == 45) || (ch == 46) || (ch == 64);
	}

	private static boolean isSubsequent(int ch) {
		return (isInitial(ch)) || (isDigit(ch)) || (isSpecialSubsequent(ch));
	}

	private static byte checkByte(int ch) {
		if ((ch < 0) || (ch > 255)) {
			System.err.println("Illegal character code '" + ch + "'");
		}
		return (byte) ch;
	}

	public Token getNextToken() {
		try {
			int ch = this.in.read();

			while ((ch != -1)
					&& ((ch == 32) || (ch == 9) || (ch == 10) || (ch == 13)
							|| (ch == 12) || (ch == 59))) {
				if (ch == 59) {
					do {
						ch = this.in.read();
					} while ((ch != -1) && (ch != 10) && (ch != 13));
				}
				if (ch != -1) {
					ch = this.in.read();
				}
			}
			if (ch == -1) {
				return null;
			}

			if (ch == 39) {
				return new Token(0);
			}
			if (ch == 40) {
				return new Token(1);
			}
			if (ch == 41) {
				return new Token(2);
			}
			if (ch == 46) {
				return new Token(3);
			}

			if (ch == 35) {
				ch = this.in.read();

				if (ch == 116) {
					return new Token(4);
				}
				if (ch == 102) {
					return new Token(5);
				}
				if (ch == -1) {
					System.err.println("Unexpected EOF following #");
					return null;
				}

				System.err
						.println("Illegal character '" + ch + "' following #");

				return getNextToken();
			}

			if (ch == 34) {
				int i = 0;
				do {
					ch = this.in.read();
					this.buf[i] = checkByte(ch);
					i++;
				} while ((ch != -1) && (ch != 34));

				if (ch == -1) {
					System.err.println("Unexpected EOF in string");
					return null;
				}

				return new StrToken(new String(this.buf, 0, i - 1));
			}

			if (isDigit(ch)) {
				int i = 0;
				do {
					i = 10 * i + (ch - 48);
					ch = this.in.read();
				} while (isDigit(ch));

				this.in.unread(ch);

				return new IntToken(i);
			}

			if ((isInitial(ch)) || (isPeculiar(ch))) {
				int i = 0;

				if ((ch >= 65) && (ch <= 90)) {
					ch = ch - 65 + 97;
				}
				this.buf[i] = checkByte(ch);
				i++;

				if (isPeculiar(ch)) {
					return new IdentToken(new String(this.buf, 0, i));
				}
				do {
					ch = this.in.read();

					if ((ch >= 65) && (ch <= 90)) {
						ch = ch - 65 + 97;
					}
					this.buf[i] = checkByte(ch);
					i++;
				} while ((ch != -1) && (isSubsequent(ch)));

				this.in.unread(ch);

				return new IdentToken(new String(this.buf, 0, i - 1));
			}

			System.err.println("Illegal input character '" + ch + '\'');
			return getNextToken();
		} catch (IOException e) {
			System.err.println("IOException: " + e.getMessage());
		}
		return null;
	}
}