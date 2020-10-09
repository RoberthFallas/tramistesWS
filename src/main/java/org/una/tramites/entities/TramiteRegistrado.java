/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author LordLalo
 */
@Entity
@Table(name = "tramites_registrados")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TramiteRegistrado implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tramites_tipos_id")
    private TramiteTipo tramiteTipo;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tramiteRegistrado", fetch = FetchType.LAZY)
    private List<TramiteCambioEstado> tramiteCambioEstados = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tramiteRegistrado", fetch = FetchType.LAZY)
    private List<Nota> notas = new ArrayList<>();


}
