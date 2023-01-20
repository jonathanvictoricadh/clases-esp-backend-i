package com.dh.course.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "COURSE_STUDENT")
public class CourseStudent {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COURSE_STUDENT_ID", unique = true, nullable = false)
    private Long courseStudentId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "COURSE_ID", nullable = false, referencedColumnName = "COURSE_ID"
            , foreignKey = @ForeignKey(name = "fk_CourseStudent_Course"))
    @JsonIgnore
    private Course course;

    @Column(name = "STUDENT_ID", nullable = false)
    private Long studentId;
}
