package model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cartao")
public class Cartao
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name="cartao_id")
  private Long id;
  @Column(name="cartao_bandeira")
  private String cartaoBandeira;
  @Column(name="cartao_tipo")
  private String cartaoTipo;
  @Column(name="cartao_vezes")
  private int cartaoVezes;
  
  @Column(name="cartao_taxa")
  private BigDecimal cartaoTaxa;
  
  public String getCartaoBandeira()
  {
    return this.cartaoBandeira;
  }
  
  public void setCartaoBandeira(String cartaoBandeira)
  {
    this.cartaoBandeira = cartaoBandeira;
  }
  
  public int getCartaoVezes() {
	return cartaoVezes;
}

public void setCartaoVezes(int cartaoVezes) {
	this.cartaoVezes = cartaoVezes;
}

public BigDecimal getCartaoTaxa()
  {
    return this.cartaoTaxa;
  }
  
  public void setCartaoTaxa(BigDecimal cartaoTaxa)
  {
    this.cartaoTaxa = cartaoTaxa;
  }
  
  public Long getId()
  {
    return this.id;
  }
  
  public void setId(Long id)
  {
    this.id = id;
  }
  
  public String getCartaoTipo()
  {
    return this.cartaoTipo;
  }
  
  public void setCartaoTipo(String cartaoTipo)
  {
    this.cartaoTipo = cartaoTipo;
  }

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	return result;
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Cartao other = (Cartao) obj;
	if (id == null) {
		if (other.id != null)
			return false;
	} else if (!id.equals(other.id))
		return false;
	return true;
}
  

}
