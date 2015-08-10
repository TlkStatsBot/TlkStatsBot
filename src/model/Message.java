package model;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	@Column(nullable = false, name="MSG_ORIGIN") // 'from' is a keyword in sql!
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
	@Column
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
	private User forwardFrom;
	
	@JsonProperty("forward_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date forwardTime;
	
	@JsonProperty("reply_to_message")
	private Message responseToMsg;
	
	@JsonProperty
	@Column(columnDefinition = "NVARCHAR(MAX)")
	private String text;
	
	@JsonProperty
	private Audio audio;
	
	@JsonProperty
	private Document document;
	
	@JsonProperty
	@ManyToMany
	@JoinTable(name="Message_PhotoSize_photo")
	private List<PhotoSize> photo;
	
	@JsonProperty
	private Sticker sticker;
	
	@JsonProperty
	private Video video;
	
	@JsonProperty
	private String caption;
	
	@JsonProperty
	private Contact contact;
	
	@JsonProperty
	private Location location;
	
	@JsonProperty("new_chat_participant")
	private User userJoin;
	
	@JsonProperty("left_chat_participant")
	private User userPart;
	
	@JsonProperty("new_chat_title")
	private String newChatTitle;
	
	@JsonProperty("new_chat_photo")
	@ManyToMany
	@JoinTable(name="Message_PhotoSize_NewChat")
	private List<PhotoSize> newChatPhoto;
	
	@JsonProperty("delete_char_photo")
	private boolean charPhotoDeleted;
	
	@JsonProperty("group_chat_created")
	private boolean newGroup;

	public String toString() {
		return PrintHelper.toString(this);
	}
}
