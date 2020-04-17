package com.model;

import java.util.Date;


//@NamedQuery(name="employee.left",
//			query="SELECT e FROM Employee e left join orders o where e.eid=o.eid")
public class EmployeeOrdersJoinColumn {
	private Integer seq;
	private Integer eid;
	private String ename;
	private Integer oid;
	private Date createdate;
	private Integer cid;
	
	public EmployeeOrdersJoinColumn() {}
	public EmployeeOrdersJoinColumn(Integer seq, Integer eid, String ename, Integer oid, Date createdate, Integer cid) {
		this.seq = seq;
		this.eid = eid;
		this.ename = ename;
		this.oid = oid;
		this.createdate = createdate;
		this.cid = cid;
	}

	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public Integer getEid() {
		return eid;
	}

	public void setEid(Integer eid) {
		this.eid = eid;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public Integer getOid() {
		return oid;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}


	
	
	
}
