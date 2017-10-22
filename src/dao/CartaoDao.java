package dao;

import javax.swing.JOptionPane;
import model.Cartao;

public class CartaoDao
  extends GenericDao<Cartao>
{
  public void salvar(Cartao car)
  {
    save(car);
  }
  
  public void alterar(Cartao car)
  {
    update(car);
  }
  
  public void excluir(long id)
  {
    Cartao v = (Cartao)findById(id);
    if (v != null)
    {
      delete(v);
      JOptionPane.showMessageDialog(null, "Cartao excluído com sucesso!");
    }
    else
    {
      JOptionPane.showMessageDialog(null, "Cartao inexistente!");
    }
  }

public int buscarNumeroVezes(long id) {
	Cartao v = (Cartao)findById(id);
	return v.getCartaoVezes();
}

public String buscarCreOudeb(long cartao) {
	Cartao c = (Cartao)findById(cartao);
	String res = c.getCartaoTipo();
	return res;
}


}
