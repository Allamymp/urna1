package org.ifpe.dws3.prova1.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name="name")
    private String name;

    @Column(name="number", nullable = false)
    private Integer number;

    private Integer votes = 0;

    @ManyToOne
    @JoinColumn(name = "party_id")
    private PoliticalParty party;



    public void addVote() {
        this.votes++;
    }
}
