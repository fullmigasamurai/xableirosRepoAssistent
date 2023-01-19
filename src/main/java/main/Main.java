
package main;

import view.XableirosRepoAssistente;

/**
 *
 * @author ENTRAPTA
 */
public class Main {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
		/* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
		* For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
		*/
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					javax.swing.UIDefaults defaults = javax.swing.UIManager.getLookAndFeelDefaults();
					defaults.put("Table.gridColor", new java.awt.Color (214,217,223));
					defaults.put("Table.disabled", false);
					defaults.put("Table.showGrid", true);
					defaults.put("Table.intercellSpacing", new java.awt.Dimension(1, 1));
					break;
				}
				
			}
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(XableirosRepoAssistente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
            //</editor-fold>
            
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
			
				new XableirosRepoAssistente().setVisible(true);
			}
		});
	}
}
