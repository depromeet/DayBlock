package com.depromeet.dayblock.block.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@Builder
@Table(name = "block")
public class Block {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "block_id")
    private Long id;

    @Column(nullable = false, length = 50)
    private String title;

    private String memo;

    private String link;

    @Column(nullable = false)
    private int location;

    // @OneToMany
    // private User owner;

    @Column(nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private BlockPriority priority;

    @Column(name = "scheduled_date", nullable = false)
    private LocalDate scheduledDate;

    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;

    @Enumerated(EnumType.STRING)
    private BlockStatus blockStatus;

    @Column(nullable = false, length = 30)
    private String category;

    public void validate() {
        if (this.title.isEmpty()) {
            throw new IllegalArgumentException("'title' 값을 입력해주세요.");
        }
        if (this.priority == null) {
            throw new IllegalArgumentException("'priority' 값을 입력해주세요.");
        }
        if (this.scheduledDate == null) {
            throw new IllegalArgumentException("'scheduledDate' 값을 입력해주세요.");
        }
        if (this.category.isEmpty()) {
            throw new IllegalArgumentException("'category' 값을 입력해주세요.");
        }
    }
}
