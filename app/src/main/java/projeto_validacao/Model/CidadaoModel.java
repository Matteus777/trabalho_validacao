/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projeto_validacao.Model;

import java.util.List;

/**
 *
 * @author Matteus
 */
public class CidadaoModel {
    private int id;
    private String nome;
    private String documento;
    private String endereco;
    private String telefone;
    private String email;
    private List<OcorrenciaModel> ocorrencias;

    public CidadaoModel(int id, String nome, String documento, String endereco, String telefone, String email, List<OcorrenciaModel> ocorrencias) {
        this.id = id;
        this.nome = nome;
        this.documento = documento;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.ocorrencias = ocorrencias;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
 
   
    
    public List<OcorrenciaModel> getOcorrencias() {
        return ocorrencias;
    }
    
    public void setOcorrencias(List<OcorrenciaModel> ocorrencias) {
        this.ocorrencias = ocorrencias;
    }
    
    
    public CidadaoModel() {
    }
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
