package com.leoman.pg2.entity;

import java.util.Date;

import javax.persistence.*;

import com.leoman.doctor.entity.Doctor;
import org.hibernate.annotations.GenericGenerator;

/**
 * 病理 / 讲座
 * @author yesong
 *
 */
@Entity
@Table(name = "t_pathology_v2")
public class Pg2 {

	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	private String id;
	
	@Column(name = "TITLE")
	private String title ;
	
	@Column(name = "CONTENT")
	private String content;
	
	@Column(name = "IMAGE")
	private String image;
	
	@Column(name = "URL")
	private String url;
	
	@Column(name = "TYPE")
	private Integer type; // 类型 0:病理 1:讲座

	@ManyToOne
	@JoinColumn(name = "doctor_id")
	private Doctor doctor;

	@Column(name = "CREATE_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
}
