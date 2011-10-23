package com.smartworks.invtmgmt.core.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="item")
public class Item implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/** Field mapping. */
	@Column(name="item_desc",length=200)
	private String desc;
	
	/** Field mapping. */
	@Id
	@Column(name="item_id")
	private Integer id;
	
	/** Field mapping. */
	@Column(name="name", length=50)
	private String name;
	
	@OneToMany (mappedBy="item")
	Set<ItemAttributeMapping> attributeMappings = new HashSet<ItemAttributeMapping>();
	
	/**
	 * Default constructor, mainly for hibernate use.
	 */
	public Item() {
		// Default constructor
	} 
	
	/** Constructor taking a given ID.
	 * @param id to set
	 */
	public Item(Integer id) {
		this.id = id;
	}
	
	/** Constructor taking a given ID.
	 * @param id Integer object;
	 * @param name String object;
	 */
	public Item(Integer id, String name) {

		this.id = id;
		this.name = name;
	}
	
	
	/** Return the type of this class. Useful for when dealing with proxies.
	* @return Defining class.
	*/
	@Transient
	public Class<?> getClassType() {
		return Item.class;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<ItemAttributeMapping> getAttributeMappings() {
		return attributeMappings;
	}

	public void setAttributeMappings(Set<ItemAttributeMapping> attributeMappings) {
		this.attributeMappings = attributeMappings;
	}	
	
}
