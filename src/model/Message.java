package model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Message {

	@JsonProperty("message_id")
	private int id;
	
	@JsonProperty
	private User from;
	
	@JsonProperty
	private Date date;
	
	@JsonProperty
	private Object chat;
	
	// (Optional fields)
	
	@JsonProperty("forward_from")
	private User forwardFrom;
	
	@JsonProperty("forward_time")
	private Date forwardTime;
	
	@JsonProperty("reply_to_message")
	private Message responseToMsg;
	
	@JsonProperty
	private String text;
	
	@JsonProperty
	private Audio audio;
	
	@JsonProperty
	private Document document;
	
	@JsonProperty
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
	private List<PhotoSize> newChatPhoto;
	
	@JsonProperty("delete_char_photo")
	private boolean charPhotoDeleted;
	
	@JsonProperty("group_chat_created")
	private boolean newGroup;

	public String toString() {
		return PrintHelper.toString(this);
	}
}
