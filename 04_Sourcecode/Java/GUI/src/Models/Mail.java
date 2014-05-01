package Models;

import java.util.Date;
import java.util.Properties;
import java.util.Vector;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Mail {

	private static Properties props = null;
	private static Session session = null;
	private static Message message = null;
	private static Multipart multipart = null;

	// public static void main(String[] args) {
	// Vector<String> attachmentList = new Vector<>();
	// attachmentList.add("E:\\soft\\virtualbox\\License_en_US.rtf");
	// attachmentList.add("E:\\soft\\unikey42RC1-131228-win32\\keymap.txt");
	// init("nhacso004@gmail.com", "761311kh?@");
	// setInfo("nhacso004@gmail.com", "khangml@accjpn.com",
	// "Tiêu đề ứng dụng gửi thư.!", "Nội dung ứng dụng gửi thư.!");
	// setAttachments("Bên trong ứng dụng gửi thư", attachmentList);
	// System.out.println(send());
	// }
	private static void reset() {
		props = null;
		session = null;
		message = null;
		multipart = null;
	}

	public static String send() {
		String rs = "";
		try {
			Transport.send(message);
			rs = "Done";
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			rs = e.getMessage();
		}
		reset();
		return rs;
	}

	public static void setInfo(String fromEmail, String toEmail,
			String subject, String content) {
		try {
			message = new MimeMessage(session);
			message.setHeader("Content-Type", "text/html; charset=UTF-8");
			message.setFrom(new InternetAddress(fromEmail));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(toEmail));
			message.setSubject(subject);
			message.setText(content);// xuong hang \n

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	public static void setAttachments(String body, Vector<String> fileList) {
		try {
			multipart = new MimeMultipart();

			MimeBodyPart mbp1 = new MimeBodyPart();
			mbp1.setContent(body, "text/html; charset=\"utf-8\"");
			mbp1.setHeader("Content-Type", "text/html; charset=\"utf-8\"");
			mbp1.setHeader("Content-Transfer-Encoding", "quoted-printable");
			multipart.addBodyPart(mbp1);

			if (fileList.size() > 0) {
				MimeBodyPart mbp2 = null;
				FileDataSource fds = null;
				for (String filename : fileList) {
					addAttachment(mbp2, fds, filename);
				}
			}
			message.setContent(multipart);
			message.setSentDate(new Date());
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	private static void addAttachment(MimeBodyPart mbp2, FileDataSource fds,
			String filename) {
		mbp2 = null;
		fds = null;
		mbp2 = new MimeBodyPart();
		fds = new FileDataSource(filename);
		try {
			mbp2.setDataHandler(new DataHandler(fds));
			mbp2.setFileName(fds.getName());
			multipart.addBodyPart(mbp2);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void init(final String email, final String password) {
		props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(email, password);
					}
				});
	}
}
