package ni.org.ics.webapp.language;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import ni.org.ics.webapp.domain.BaseMetaData;
import ni.org.ics.webapp.domain.audit.Auditable;
import org.codehaus.jackson.annotate.JsonIgnore;


@Entity
@Table(name = "mensajes", catalog = "a2cares", uniqueConstraints={@UniqueConstraint(columnNames = {"catRoot" , "catKey"})})
public class MessageResource implements Serializable, Auditable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String messageKey;
    private String catRoot;
    private String catKey;
    private char isCat = '0';
    private int order=0;
    private String spanish;
    private String english;
    private Date recordDate;
    private String recordUser;
    private String recordIp;
    private char pasive = '0';
    
    public MessageResource() {

	}

    @Id
    @Column(name = "messageKey", nullable = false, length = 100)
    public String getMessageKey() {
        return messageKey;
    }

    public void setMessageKey(String messageKey) {
        this.messageKey = messageKey;
    }
    
    @Column(name = "catRoot", nullable = true, length = 50)
    public String getCatRoot() {
		return catRoot;
	}

	public void setCatRoot(String catRoot) {
		this.catRoot = catRoot;
	}

	@Column(name = "catKey", nullable = true, length = 50)
    public String getCatKey() {
		return catKey;
	}

	public void setCatKey(String catKey) {
		this.catKey = catKey;
	}
	
	@Column(name="isCat", nullable = false, length = 1)
	public char getIsCat() {
		return isCat;
	}

	public void setIsCat(char isCat) {
		this.isCat = isCat;
	}

	@Column(name="orden", nullable = false)
	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	@Column(name = "en", nullable = true, length = 255)
    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    
    @Column(name = "es", nullable = true, length = 255)
    public String getSpanish() {
		return spanish;
	}

	public void setSpanish(String spanish) {
		this.spanish = spanish;
	}

    @Temporal( TemporalType.TIMESTAMP)
    @Column(name="recordDate")
    @JsonIgnore
    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    @Column(name="recordUser", length = 50)
    @JsonIgnore
    public String getRecordUser() {
        return recordUser;
    }

    public void setRecordUser(String recordUser) {
        this.recordUser = recordUser;
    }

    @Column(name="pasive", nullable = false, length = 1)
    public char getPasive() {
        return pasive;
    }

    public void setPasive(char pasive) {
        this.pasive = pasive;
    }

    @Column(name="recordIp", length = 50)
    @JsonIgnore
    public String getRecordIp() {
        return recordIp;
    }

    public void setRecordIp(String recordIp) {
        this.recordIp = recordIp;
    }

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MessageResource that = (MessageResource) o;

        if (messageKey != null ? !messageKey.equals(that.messageKey) : that.messageKey != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return messageKey != null ? messageKey.hashCode() : 0;
    }
    
	@Override
	public boolean isFieldAuditable(String fieldname) {
        //Campos no auditables en la tabla
        if(fieldname.matches("recordDate")||fieldname.matches("recordUser")){
            return false;
        }
        return true;
	}

	

}
