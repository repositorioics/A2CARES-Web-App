package ni.org.ics.webapp.users.model;

import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import ni.org.ics.webapp.domain.audit.Auditable;

import ni.org.ics.webapp.domain.personal.Personal;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.validator.constraints.NotBlank;


/**
 * Simple objeto de dominio que representa un usuario
 * 
 * @author William Aviles
 **/

@Entity
@Table(name = "usuarios_sistema", catalog = "a2cares")
public class UserSistema implements Auditable {
	private String username;
	private Date created;
	private Date modified;
	private Date lastAccess;
	private String password;
	private String completeName;
	private String email;
	private Boolean enabled=true;
	private Boolean accountNonExpired=true;
	private Boolean credentialsNonExpired=true;
	private Date lastCredentialChange;
	private Boolean accountNonLocked=true;
	private String createdBy;
	private String modifiedBy;
    private Personal seqPersonal;
	
	@Id
	@Column(name = "NOMBRE_USUARIO", nullable = false, length =50)
	@Size(min = 5, max = 50)
	@Pattern(regexp = "^[a-zA-Z0-9]+$")
	@NotBlank
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Temporal( TemporalType.TIMESTAMP)
	@Column(name = "FECHA_REGISTRO", nullable = false)
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	@Temporal( TemporalType.TIMESTAMP)
	@Column(name = "FECHA_ULTMOD", nullable = true)
	public Date getModified() {
		return modified;
	}
	public void setModified(Date modified) {
		this.modified = modified;
	}
	@Temporal( TemporalType.TIMESTAMP)
	@Column(name = "FECHA_ULTACC", nullable = true)
	public Date getLastAccess() {
		return lastAccess;
	}
	public void setLastAccess(Date lastAccess) {
		this.lastAccess = lastAccess;
	}
	@Temporal( TemporalType.TIMESTAMP)
	@Column(name = "FECHA_ULTMODCRED", nullable = true)
	public Date getLastCredentialChange() {
		return lastCredentialChange;
	}
	public void setLastCredentialChange(Date lastCredentialChange) {
		this.lastCredentialChange = lastCredentialChange;
	}
	@Column(name = "CONTRASENA", nullable = false, length =150)
	@Size(min = 8, max = 150)
	@Pattern(regexp = "^[a-zA-Z0-9!@#$%^&*()?/]+$")
	@NotBlank
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name = "DESCRIPCION", nullable = true, length =250)
	@Size(max = 250)
	public String getCompleteName() {
		return completeName;
	}
	public void setCompleteName(String completeName) {
		this.completeName = completeName;
	}
	@Column(name = "CORREO_ELECTRONICO", nullable = true, length =100)
	@Size(max = 100)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name = "HABILITADO", nullable = false)
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}	
	@Column(name = "CUENTA_SINEXPIRAR", nullable = false)
	public Boolean getAccountNonExpired() {
		return accountNonExpired;
	}
	public void setAccountNonExpired(Boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}
	@Column(name = "CREDENCIAL_SINEXPIRAR", nullable = false)
	public Boolean getCredentialsNonExpired() {
		return credentialsNonExpired;
	}
	public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}
	@Column(name = "CUENTA_SINBLOQUEAR", nullable = false)
	public Boolean getAccountNonLocked() {
		return accountNonLocked;
	}
	public void setAccountNonLocked(Boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}
	@Column(name = "USUARIO_REGISTRO", nullable = false, length =50)
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	@Column(name = "USUARIO_ULTMOD", nullable = true, length =50)
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "SEQ_PERSONAL", nullable = true)
    @ForeignKey(name = "FK_PERSONAL_USUARIO")
    public Personal getSeqPersonal() {
        return seqPersonal;
    }

    public void setSeqPersonal(Personal seqPersonal) {
        this.seqPersonal = seqPersonal;
    }

    @Override
	public String toString(){
		return username;
	}
	@Override
	public boolean isFieldAuditable(String fieldname) {
		if(fieldname.matches("created")||fieldname.matches("createdBy")||fieldname.matches("modified")||fieldname.matches("modifiedBy")||fieldname.matches("password")){
			return false;
		}
		return true;
	}
	
	@Override
	public boolean equals(Object other) {
		
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof UserSistema))
			return false;
		
		UserSistema castOther = (UserSistema) other;

		return (this.getUsername().equals(castOther.getUsername()));
	}
	
	@Override
	public int hashCode(){
		int result = 0;
		result = 31*result + (username !=null ? username.hashCode() : 0);
		return result;
	}
}
