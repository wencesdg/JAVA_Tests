package io.gongarce.ud2_mvc.infra.jdbc.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Gonzalo
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "person", catalog = "AMDI_24", uniqueConstraints = @UniqueConstraint(columnNames = {"nif"}))
@NamedQueries({
    @NamedQuery(name = "Person.findAll", query = "SELECT p FROM PersonEntity p"),
    @NamedQuery(name = "Person.findByNif", query = "SELECT p FROM PersonEntity p WHERE p.nif = :nif"),
    @NamedQuery(name = "Person.findByMail", query = "SELECT p FROM PersonEntity p RIGHT JOIN mails m WHERE m.address LIKE :mail "),
    @NamedQuery(name = "Person.findByPhone", query = "SELECT p FROM PersonEntity p WHERE :phone MEMBER OF p.phones")})
public class PersonEntity {

    @Id
    @Basic(optional = false)
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nif")
    String nif;

    @Column(name = "name")
    String name;

    @Column(name = "place")
    String place;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
    List<MailEntity> mails;

    @ElementCollection
    @CollectionTable(name = "phone", catalog = "AMDI_24", joinColumns = {
        @JoinColumn(name = "id_person")})
    @Column(name = "phone")
    List<String> phones;
}
