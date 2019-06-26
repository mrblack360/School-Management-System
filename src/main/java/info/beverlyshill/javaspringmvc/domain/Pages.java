package info.beverlyshill.javaspringmvc.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Model object for Pages table
 * 
 * @author bhill2
 */
@Entity
@Table(name = "Pages")
public class Pages implements Serializable {

	int pageId;
	String name;
	String textDesc;
	String detailPage;

	public Pages() {

	}

	public Pages(String name, String textDesc, String detailPage) {
		this.name = name;
		this.textDesc = textDesc;
		this.detailPage = detailPage;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "pageId")
	public int getPageId() {
		return this.pageId;
	}

	public void setPageId(int pageId) {
		this.pageId = pageId;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "textDesc")
	public String getTextDesc() {
		return textDesc;
	}

	public void setTextDesc(String textDesc) {
		this.textDesc = textDesc;
	}

	@Column(name = "detailPage")
	public String getDetailPage() {
		return detailPage;
	}

	public void setDetailPage(String detailPage) {
		this.detailPage = detailPage;
	}
}
