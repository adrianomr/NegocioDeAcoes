package NegocioDeAcoes.NegocioDeAcoes.model;

import NegocioDeAcoes.NegocioDeAcoes.interfaces.IMonitorEmissorPressos;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.annotation.PostConstruct;
import javax.persistence.*;

@Entity
@Table(name = "monitoramento")
public class Monitoramento implements IMonitorEmissorPressos {

    @Id
    @GeneratedValue(generator = "answer_generator")
    @SequenceGenerator(
            name = "answer_generator",
            sequenceName = "answer_sequence",
            initialValue = 0
    )
    private Long id;

    @Column(columnDefinition = "varchar(100)")
    private String empresa;

    @Column(columnDefinition = "numeric(20,4)")
    private Double precoCompra;

    @Column(columnDefinition = "numeric(20,4)")
    private Double precoVenda;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "conta_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    private Conta conta;

    @Transient
    private double precoAtual;

    public Monitoramento() {
        precoAtual = 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public Double getPrecoCompra() {
        return precoCompra;
    }

    public void setPrecoCompra(Double precoCompra) {
        this.precoCompra = precoCompra;
    }

    public Double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(Double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    @Override
    public void setPreco(double preco) {
        this.precoAtual = preco;
        System.out.println(preco);
    }

    @Override
    public String toString() {
        return "Monitoramento{" +
                "id=" + id +
                ", empresa='" + empresa + '\'' +
                ", precoCompra=" + precoCompra +
                ", precoVenda=" + precoVenda +
                ", conta=" + conta +
                ", precoAtual=" + precoAtual +
                '}';
    }
}
