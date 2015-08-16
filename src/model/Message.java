package model;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import util.PrintHelper;

@Entity
public class Message implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("message_id")
	@Id
	private int id;
	
	@JsonProperty
	@JoinColumn(nullable = false, name="MSG_ORIGIN") // 'from' is a keyword in sql!
	@ManyToOne(cascade = {CascadeType.ALL})
	private User from;
	
	@JsonProperty
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	/*
	 * Basically, 
	 * chat == null if this is a private message
	 * OR
	 * chat instanceof GroupChat if this is not a private message.
	 * 
	 * (The parsing is not that easy because in the json string, this field contains either
	 * a GroupChat or a User (which equal to the one in the 'from' attribute)
	 */
	@JsonProperty
	@ManyToOne(cascade = {CascadeType.ALL})
	private GroupChat chat;
	
	@JsonProperty
	public GroupChat getChat() {
		return chat;
	}
	
	@JsonProperty
	public void setChat(Object chat) {	
		if (chat instanceof LinkedHashMap) {
			// The json will call this method with a LinkedHashMap<String, String>
			try {
				@SuppressWarnings("unchecked")
				LinkedHashMap<String, String> json = (LinkedHashMap<String, String>) chat;
				ObjectMapper mapper = new ObjectMapper();
				
				GroupChat groupChat = mapper.convertValue(json, GroupChat.class);
				setChat(groupChat);
			} catch (Exception ignored) {
				// LinkedHashMap most likely represents a User, so this is a private message.
				this.chat = null;
			}
		} else 	if (chat instanceof GroupChat) {
			this.chat = (GroupChat) chat;
		} else {
			this.chat = null;
		}
	}
	
	@JsonProperty("forward_from")
	@ManyToOne(cascade = {CascadeType.ALL})
	private User forwardFrom;
	
	@JsonProperty("forward_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date forwardTime;
	
	@JsonProperty("reply_to_message")
	@ManyToOne(cascade = CascadeType.MERGE)
	private Message responseToMsg;
	
	@JsonProperty
	@Column(columnDefinition = "NVARCHAR(MAX)")
	private String text;
	
	@JsonProperty
	@ManyToOne(cascade = CascadeType.MERGE)
	private Audio audio;
	
	@JsonProperty
	@ManyToOne(cascade = CascadeType.MERGE)
	private Document document;
	
	@JsonProperty
	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name="Message_PhotoSize_photo")
	private List<PhotoSize> photo;
	
	@JsonProperty
	@ManyToOne(cascade = CascadeType.MERGE)
	private Sticker sticker;
	
	@JsonProperty
	@ManyToOne(cascade = CascadeType.MERGE)
	private Video video;
	
	@JsonProperty
	private String caption;
	
	@JsonProperty
	@ManyToOne(cascade = CascadeType.MERGE)
	private Contact contact;
	
	@JsonProperty
	@ManyToOne(cascade = CascadeType.MERGE)
	private Location location;
	
	@JsonProperty("new_chat_participant")
	@ManyToOne(cascade = CascadeType.MERGE)
	private User userJoin;
	
	@JsonProperty("left_chat_participant")
	@ManyToOne(cascade = CascadeType.MERGE)
	private User userPart;
	
	@JsonProperty("new_chat_title")
	private String newChatTitle;
	
	@JsonProperty("new_chat_photo")
	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name="Message_PhotoSize_NewChat")
	private List<PhotoSize> newChatPhoto;
	
	@JsonProperty("delete_char_photo")
	private boolean charPhotoDeleted;
	
	@JsonProperty("group_chat_created")
	private boolean newGroup;

	@Override
	public int hashCode() {
		return id;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Message) {
			return id == ((Message) obj).id;
		}
		return false;
	}
	
	public String toString() {
		return PrintHelper.toString(this);
	}
}
