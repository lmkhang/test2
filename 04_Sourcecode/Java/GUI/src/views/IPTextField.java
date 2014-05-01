package views;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class IPTextField extends PlainDocument {
	private int min;
	private int max;

	IPTextField(int min, int max) {
		super();
		this.min = min;
		this.max = max;
	}

	IPTextField(int min, int max, boolean upper) {
		super();
		this.min = min;
		this.max = max;
	}

	public void insertString(int offset, String str, AttributeSet attr)
			throws BadLocationException {
		if (str == null)
			return;

		if (getLength() == 0) {
			super.insertString(offset, str, attr);
		} else if (getLength() == 1) {
			super.insertString(offset, str, attr);
		} else if (getLength() == 2) {
			String ab1 = getContent().getString(0, getLength());
			String after = null;
			if (offset == 0) {
				after = str + ab1;
				
			} else if (offset == 2) {
				after = ab1 + str;
			} else if(offset == 1){
				char[] chs = ab1.toCharArray();
				after = chs[0]+""+str+""+chs[1];
			}
			int full;
			try {
				full = Integer.parseInt(after);
			} catch (Exception e) {
				// TODO: handle exception
				return;
			}
			if (0 <= full && full <= 255 && ((getLength() + str.length()) <= 3)) {
				super.insertString(offset, str, attr);
			}
		}
		
	}

}
