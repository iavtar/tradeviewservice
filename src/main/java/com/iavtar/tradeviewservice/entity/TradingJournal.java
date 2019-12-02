package com.iavtar.tradeviewservice.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;


/**
 * @author indra singh
 * */
@Entity
@Table(name = "trade_journal")
@Data
public class TradingJournal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "trade_journal_id")
	private Long tradeJournalId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "trading_account_id")
	@JsonIgnore
	private TradingAccount tradingAccount;

	@Column(name = "ticker")
	private String ticker;

	@Column(name = "duration")
	private int duration;

	@Column(name = "direction")
	private int direction;

	@Column(name = "profit")
	private double profit;

	@Column(name = "commission")
	private double commission;

	@Column(name = "volume")
	private double volume;

	@Column(name = "enter_price")
	private double enterPrice;

	@Column(name = "exit_price")
	private double exitPrice;

	@Column(name = "enter_reason")
	private String enterReason;

	@Column(name = "exit_reason")
	private String exitReason;

	@Column(name = "comment")
	private String comment;

	@Column(name = "status")
	private String status;

	private Date createdAt;

	private Date updatedAt;

	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}

}
