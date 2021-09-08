package ni.org.ics.webapp.domain.audit;

public interface Auditable {
	
	public boolean isFieldAuditable(String fieldname);

}
