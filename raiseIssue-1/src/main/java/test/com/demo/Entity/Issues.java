package test.com.demo.Entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Issue")
public class Issues {
	
	
	@Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   
	@Column(name="associateId")
   private Long associateId;

	@Column(name="issueSummary")
   private String issueSummary;
   
   @Column(name="issueType")
   private String issueType;

   @Column(name="description")
   private String description;

   @Column(name="sourceIP")
   private String sourceIP;

   @Column(name="destinationIP")
   private String destinationIP;

   @Column(name="vpn")
   private String vpn;

   @Column(name="connectivity")
   private String connectivity;


   @Column(name="status")
   private String status;

   @Column(name="datetime")
   private String datetime;
   
//   default constructor
   public Issues() {
   }
   
	public Issues(Long id, Long associateId, String issueSummary, String issueType, String description,
			String sourceIP, String destinationIP, String vpn, String connectivity, 
			String status, String datetime) {
		super();
		this.id = id;
		this.associateId = associateId;
		this.issueSummary = issueSummary;
		this.issueType = issueType;
		this.description = description;
		this.sourceIP = sourceIP;
		this.destinationIP = destinationIP;
		this.vpn = vpn;
		this.connectivity = connectivity;
		this.status = status;
		this.datetime = datetime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAssociateId() {
		return associateId;
	}

	public void setAssociateId(Long associateId) {
		this.associateId = associateId;
	}

	public String getissueSummary() {
		return issueSummary;
	}

	public void setissueSummary(String issueSummary) {
		this.issueSummary = issueSummary;
	}

	public String getIssueType() {
		return issueType;
	}

	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}

	public String getdescription() {
		return description;
	}

	public void setdescription(String description) {
		this.description = description;
	}

	public String getSourceIP() {
		return sourceIP;
	}

	public void setSourceIP(String sourceIP) {
		this.sourceIP = sourceIP;
	}

	public String getDestinationIP() {
		return destinationIP;
	}

	public void setDestinationIP(String destinationIP) {
		this.destinationIP = destinationIP;
	}

	public String getVpn() {
		return vpn;
	}

	public void setVpn(String vpn) {
		this.vpn = vpn;
	}

	public String getconnectivity() {
		return connectivity;
	}

	public void setconnectivity(String connectivity) {
		this.connectivity = connectivity;
	}

	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getdatetime() {
		return datetime;
	}

	public void setdatetime(String datetime) {
		this.datetime = datetime;
	}

   
   
}