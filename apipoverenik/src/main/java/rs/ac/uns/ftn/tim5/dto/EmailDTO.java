package rs.ac.uns.ftn.tim5.dto;


import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "recipient", "subject", "message", "attachmentPdf", "attachmentHtml" })
@XmlRootElement(name = "email")
public class EmailDTO {

	@XmlElement(required = true)
	private String recipient;

	@XmlElement(required = true)
	private String subject;

	@XmlElement(required = true)
	private String message;

	@XmlElement(required = true)
	private byte[] attachmentPdf;

	@XmlElement(required = true)
	private byte[] attachmentHtml;

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public byte[] getAttachmentPdf() {
		return attachmentPdf;
	}

	public void setAttachmentPdf(byte[] attachmentPdf) {
		this.attachmentPdf = attachmentPdf;
	}

	public byte[] getAttachmentHtml() {
		return attachmentHtml;
	}

	public void setAttachmentHtml(byte[] attachmentHtml) {
		this.attachmentHtml = attachmentHtml;
	}
}
