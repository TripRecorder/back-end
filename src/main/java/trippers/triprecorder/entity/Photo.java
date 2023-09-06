package trippers.triprecorder.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Photo {
    //photo
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //post 번호 단방향
    @ManyToOne
    @JoinColumn(name = "post_id")
    private SnsVO sns;

    @Column(nullable = false)
    private String photo;

}
