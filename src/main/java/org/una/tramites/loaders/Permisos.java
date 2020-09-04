/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.loaders;

/**
 *
 * @author LordLalo
 */
public enum Permisos {
    CrearUsuario("USU1"),
    ModificarUsuario("USU2"),
    InactivarUsuario("USU3"),
    ConsultarUsuario("USU4"),
    CrearDepartamento("DEP1"),
    ModificarDepartamento("DEP2"),
    InactivarDepartamento("DEP3"),
    ConsultarDepartamento("DEP4"),
    RegistrarTramite("TRA1"),
    ModificarTramite("TRA2"),
    InactivarTramite("TRA3"),
    FinalizarTramite("TRA4"),
    ConsultarTramite("TRA5"),
    ConsultarTodosTramites("TRA6"),
    DiseñarTramitesVariacionesRequisitos("TRD1"),
    ConsultarReportesTransacciones("TRU1");
    private String codigo;

    Permisos(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }
}


