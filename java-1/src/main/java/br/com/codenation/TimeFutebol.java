package br.com.codenation;

import java.time.LocalDate;

public class TimeFutebol {

    private Long id;
    private String nome;
    private LocalDate dataCriacao;
    private String corUniformePrincipal;
    private String corUniformeSecundario;
    private Jogador jogadorCapitaoTime;

    public TimeFutebol() {
    }

    public TimeFutebol(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario, Jogador jogadorCapitaoTime) {
        this.id = id;
        this.nome = nome;
        this.dataCriacao = dataCriacao;
        this.corUniformePrincipal = corUniformePrincipal;
        this.corUniformeSecundario = corUniformeSecundario;
        this.jogadorCapitaoTime = jogadorCapitaoTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getCorUniformePrincipal() {
        return corUniformePrincipal;
    }

    public void setCorUniformePrincipal(String corUniformePrincipal) {
        this.corUniformePrincipal = corUniformePrincipal;
    }

    public String getCorUniformeSecundario() {
        return corUniformeSecundario;
    }

    public void setCorUniformeSecundario(String corUniformeSecundario) {
        this.corUniformeSecundario = corUniformeSecundario;
    }

    public Jogador getJogadorCapitaoTime() {
        return jogadorCapitaoTime;
    }

    @Override
    public String toString() {
        return "TimeFutebol{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", dataCriacao=" + dataCriacao +
                ", corUniformePrincipal='" + corUniformePrincipal + '\'' +
                ", corUniformeSecundario='" + corUniformeSecundario + '\'' +
                ", jogadorCapitaoTime=" + jogadorCapitaoTime +
                '}';
    }

    public void setJogadorCapitaoTime(Jogador jogadorCapitaoTime) {
        this.jogadorCapitaoTime = jogadorCapitaoTime;
    }


}
