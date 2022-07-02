/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projeto_validacao.Model;

import java.util.Date;

/**
 *
 * @author Matteus
 */
public class OcorrenciaModel {

    private int id;
    private String local;
    private Date data;
    private String descricao;
    private int cidadaoId;

    public OcorrenciaModel(int id, String local, Date data, String descricao ) {
        this.id = id;
        this.local = local;
        this.data = data;
        this.descricao = descricao;
    }

    public OcorrenciaModel() {
    }

    public int getCidadaoId() {
        return cidadaoId;
    }

    public void setCidadaoId(int cidadaoId) {
        this.cidadaoId = cidadaoId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
