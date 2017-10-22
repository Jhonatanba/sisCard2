package filter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class VendaFilter
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private Date data;
  private Date dataAte;
  private Date dataPrev;
  private BigDecimal valorVenda;
  private String usuario;
  private String cartao;
  private Long id;
  private String statusPrevisao;
  private String statusPagamento;
  
  public Date getData()
  {
    return this.data;
  }
  
  public void setData(Date data)
  {
    this.data = data;
  }
  
  public String getUsuario()
  {
    return this.usuario;
  }
  
  public void setUsuario(String usuario)
  {
    this.usuario = usuario;
  }
  
  public String getCartao()
  {
    return this.cartao;
  }
  
  public void setCartao(String cartao)
  {
    this.cartao = cartao;
  }
  
  public Long getId()
  {
    return this.id;
  }
  
  public void setId(Long id)
  {
    this.id = id;
  }
  
  public Date getDataAte()
  {
    return this.dataAte;
  }
  
  public void setDataAte(Date dataAte)
  {
    this.dataAte = dataAte;
  }
  
  public BigDecimal getValorVenda()
  {
    return this.valorVenda;
  }
  
  public void setValorVenda(BigDecimal valorVenda)
  {
    this.valorVenda = valorVenda;
  }
  
  public String getStatusPrevisao()
  {
    return this.statusPrevisao;
  }
  
  public void setStatusPrevisao(String statusPrevisao)
  {
    this.statusPrevisao = statusPrevisao;
  }
  
  public String getStatusPagamento()
  {
    return this.statusPagamento;
  }
  
  public void setStatusPagamento(String statusPagamento)
  {
    this.statusPagamento = statusPagamento;
  }

public Date getDataPrev() {
	return dataPrev;
}

public void setDataPrev(Date dataPrev) {
	this.dataPrev = dataPrev;
}
}
