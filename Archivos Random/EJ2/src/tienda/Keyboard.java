package tienda;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Keyboard {

	String rString() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s = br.readLine();

		return (s);
	}

	int rInt() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int i;
		String s = br.readLine();

		try {

			i = Integer.parseInt(s);

		} catch (NumberFormatException e) {

			i = Integer.MIN_VALUE;

		}

		return (i);
	}

	double rDouble() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		double d;
		String s = br.readLine();

		try {

			d = Double.parseDouble(s);

		} catch (NumberFormatException e) {

			d = Double.MIN_VALUE;

		}

		return (d);
	}

	float rFloat() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		float f;
		String s = br.readLine();

		try {

			f = Float.parseFloat(s);

		} catch (NumberFormatException e) {

			f = Float.MIN_VALUE;
		}

		return f;
	}

	char rChar() throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		char c;

		String s = br.readLine();
		c = s.charAt(0);

		return c;

	}

	void newLine() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br.readLine();
	}
}
