package com.ikub.healthcare.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "results")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Result {
    @Id
    @GeneratedValue
    private Integer id;
    private String results;
    @ToString.Exclude
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recommandation_id", referencedColumnName = "id")
    private Recommendation recommendation;

}
