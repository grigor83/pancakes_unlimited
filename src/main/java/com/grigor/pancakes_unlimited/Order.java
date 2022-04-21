package com.grigor.pancakes_unlimited;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="order_table")
@OrderConstraint
public class Order {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
	
    @Column(name = "time")
    private LocalDateTime time;
    
    @Column(name="description")
    private String description;
    
    @ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "pancakes_list", 
			joinColumns = @JoinColumn(name = "order_id"), 
			inverseJoinColumns = @JoinColumn(name = "pancake_id")
	)
    @NotNull
	private List <Pancake> pancakes;

	public long getId() {
		return id;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Pancake> getPancakes() {
		return pancakes;
	}

	public void setPancakes(List<Pancake> pancakes) {
		this.pancakes = pancakes;
	}
}
