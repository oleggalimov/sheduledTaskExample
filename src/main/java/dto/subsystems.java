package dto;

import javax.persistence.*;

@Entity
@Table(name = "subsystems")
@NamedQuery(name = "getAll",query = "FROM subsystems")
public class subsystems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;
    @Column(name = "title")
    private String title;

    public subsystems() {
    }

    public subsystems(String name, String title) {
        this.name = name;
        this.title = title;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
