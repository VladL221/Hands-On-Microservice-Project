package vl.analysis.service.beans;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    private Long postId;

    private Long id;

    private String name;

    private String email;

    private String body;

}
