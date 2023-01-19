package com.example.carefully.domain.category.domain;

import com.example.carefully.domain.post.domain.Post;
import com.example.carefully.domain.user.entity.Role;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Slf4j
public class Category {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private final List<Post> postList = new ArrayList<>();

    private String name;

    public Category(String name) {
        this.name = name;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public boolean isAssociatedToRank() {
        try {
            Role categoryRole = Role.of(name);
            return categoryRole.isPaidRole();
        } catch (NoSuchElementException ex) {
            log.info("categoryId={} name={} 유료 회원 카테고리가 아닌 자유 카테고리 요청입니다.", id, name);
            return false;
        }
    }

    public boolean isSameRankWithUser(Role role) {
        return name.equals(role.getDescription());
    }

    public boolean isClassic() {
        return name.equals(Role.CLASSIC.getDescription());
    }
}
