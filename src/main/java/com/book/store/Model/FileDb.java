package com.book.store.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="FILES")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FileDb {
    @Id
    private String id;

    private String name;
    private String type;

    @Lob
    private byte[] data;
}
