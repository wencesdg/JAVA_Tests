package io.gongarce.ud2_mvc.infra.jdbc.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Gonzalo
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "mail", catalog = "AMDI_24", uniqueConstraints = @UniqueConstraint(columnNames = {"address"}))
@NamedQueries({
    @NamedQuery(name = "Mail.findAll", query = "SELECT m FROM MailEntity m"),
    @NamedQuery(name = "Mail.findByPerson", query = "SELECT m FROM MailEntity m WHERE m.person = :person"),
    @NamedQuery(name = "Mail.findById", query = "SELECT m FROM MailEntity m WHERE m.id = :id"),
    @NamedQuery(name = "Mail.findByAddress", query = "SELECT m FROM MailEntity m WHERE m.address = :address")})
public class MailEntity {

    @Id
    @Basic(optional = false)
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "address")
    private String address;

    @JoinColumn(name = "id_person", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private PersonEntity person;
}
