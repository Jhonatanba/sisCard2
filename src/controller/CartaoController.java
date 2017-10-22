package controller;

import dao.CartaoDao;
import java.math.BigDecimal;
import javax.swing.JOptionPane;
import model.Cartao;

public class CartaoController
{
  public void salvar(String bandeira, String tipo, int nVezes,  BigDecimal taxa)
  {
    try
    {
      Cartao cartao = new Cartao();
      cartao.setCartaoBandeira(bandeira);
      cartao.setCartaoTipo(tipo);
      cartao.setCartaoVezes(nVezes);
      cartao.setCartaoTaxa(taxa);
      
      new CartaoDao().salvar(cartao);
      JOptionPane.showMessageDialog(null, "Cartão salvo com sucesso!");
    }
    catch (Exception e)
    {
      e.printStackTrace();
      JOptionPane.showMessageDialog(null, "O cadastro não pode ser efetuado!");
    }
  }
  
  public void alterar(String codigo, String bandeira, String tipo, String qtVezes,BigDecimal taxa)
  {
    try
    {
      Cartao cartao = new Cartao();
      cartao.setId(Long.valueOf(Long.parseLong(codigo)));
      cartao.setCartaoBandeira(bandeira);
      cartao.setCartaoTipo(tipo);
      cartao.setCartaoVezes(Integer.parseInt(qtVezes));
      cartao.setCartaoTaxa(taxa);
      
      new CartaoDao().alterar(cartao);
      
      JOptionPane.showMessageDialog(null, "Cartão alterado com sucesso!");
    }
    catch (Exception e)
    {
      e.printStackTrace();
      JOptionPane.showMessageDialog(null, "O cadastro não pode ser efetuado!");
    }
  }
  
  public void excluir(Long id)
  {
    new CartaoDao().excluir(id.longValue());
  }
}
