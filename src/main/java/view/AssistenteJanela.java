package view;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.google.gson.JsonObject;

import fileAssistent.FileManipulator;
import fileAssistent.FuFile;

/**
 *
 * @author ENTRAPTA
 */
public class AssistenteJanela {

	public String userHome;
	public String xableirosHome;
	public String systemHome;
	public JsonObject config;
	private XableirosRepoAssistente janela;

	AssistenteJanela(XableirosRepoAssistente janela) {
		this.janela = janela;
		initVars();
		loadConfig();
		if (config == null) {
			setMessage("Arquivo De Configurações Não Encontrado", "WARNING");
			return;
		}
		initValues();

		// Reader reader = new BufferedReader(new FileReader(configFile.getAbsolutePath()));
		// Map<?, ?> map = new Gson().fromJson(reader, Map.class);
	}

	public void initVars () {
		userHome =(System.getProperty("user.home"));
		xableirosHome = (System.getProperty("user.home")+"/Xableiros");
		systemHome = System.getProperty("user.dir");
		config = FuFile.readFileJsonObject(systemHome+"/conf/config.json");
	}

	public void loadConfig () {
		if (config!=null) {
			this.janela.pathDsText.setText(config.get("dsPath").getAsString());
			this.janela.pathDeployText.setText(config.get("deployPath").getAsString());
			
			JsonObject colorConfig = config.get("colorConfig").getAsJsonObject();
			String hex = colorConfig.get("backGroundTextArea").getAsString();
			this.janela.backGroundColorText.setText(hex);
			int parsedResult = (int) Long.parseLong(hex, 16);
			this.janela.panel01TextArea.setBackground(new java.awt.Color(parsedResult));
			this.janela.panel02TextArea.setBackground(new java.awt.Color(parsedResult));
			this.janela.panel03TextArea.setBackground(new java.awt.Color(parsedResult));
			
			hex = colorConfig.get("foreGroundTextArea").getAsString();
			this.janela.foreGroundColorText.setText(hex);
			parsedResult = (int) Long.parseLong(hex, 16);
			this.janela.panel01TextArea.setForeground(new java.awt.Color(parsedResult));
			this.janela.panel02TextArea.setForeground(new java.awt.Color(parsedResult));
			this.janela.panel03TextArea.setForeground(new java.awt.Color(parsedResult));

		}
	}

	public void initValues () {
		loadDsDirectories();
		loadCheckBoxDs();

	}

	/**
	 * 
	 * @param message
	 */
	public void setMessage(String message) {
		setMessage(message, null);
	}

	/**
	 * 
	 * @param message
	 * @param type
	 */
	public void setMessage(String message, String type) {
		janela.messageLabel.setText(message);

		if (type == null) {
			janela.messageLabel.setForeground(new java.awt.Color(0x99FFFF));
			return;
		}
		if (type.equalsIgnoreCase("ERRO")) {
			janela.messageLabel.setForeground(new java.awt.Color(0xCC0000));
			return;
		}
		if (type.equalsIgnoreCase("WARNING")) {
			janela.messageLabel.setForeground(new java.awt.Color(0xFF9900));
		}
		if (type.equalsIgnoreCase("SUCCESS")) {
			janela.messageLabel.setForeground(new java.awt.Color(0x00FF33));
		}
	}

	/**
	 * 
	 * @param text
	 * @return
	 */
	public String pathDsText (String text) {
		this.janela.pathDsText.setText(text);
		return this.janela.pathDsText.getText();
	}

	/**
	 * 
	 * @return
	 */
	public String pathDsText () {
		if (this.janela.pathDsText.getText()==null || this.janela.pathDsText.getText().equals("")) {
			System.out.println("Caminho Para Diretório DS Vazio");
			setMessage("Caminho Para Diretório DS Vazio", "WARNING");
			return "";
		}
		return this.janela.pathDsText.getText();
	}

	public void loadDsDirectories () {
		if (this.pathDsText().equals("")) {			
			return;
		}
		try {
			File[] dsListDir = FileManipulator.listFolder(this.pathDsText());
			if (dsListDir==null) {
				System.out.println("Diretorio vazio");
				setMessage("Diretório DS vazio");
				return;
			}
			List<String> listListDir = new ArrayList<String>();

			for (File folder: dsListDir) {
				listListDir.add(folder.getName());
			}

			String[] stringListDir = listListDir.toArray(new String[0]);

			this.janela.dsPathList.setModel(new javax.swing.AbstractListModel<String>() {
				String[] strings = stringListDir;
				public int getSize() { return strings.length; }
				public String getElementAt(int i) { return strings[i]; }
			});
		} catch (Exception e) {
			System.out.println("Erro ao Carregar diretorio DS");
			e.printStackTrace();
			setMessage("Erro ao Carregar diretorio DS", "ERRO");
			throw e;
		}

	}

	public void loadCheckBoxDs () {

		try {
			JsonObject checkBoxDefault = config.get("checkBoxDefault").getAsJsonObject();
			if (checkBoxDefault==null) return;

			if (checkBoxDefault.get("comum").getAsBoolean()) {
				janela.comumCheckBox.setSelected(true);
			}
			if (checkBoxDefault.get("contrato").getAsBoolean()) {
				janela.contratoCheckBox.setSelected(true);
			}
			if (checkBoxDefault.get("patrimonio").getAsBoolean()) {
				janela.patrimonioCheckBox.setSelected(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			setMessage("Erro ao Carregar diretorio DS", "ERRO");
		}
	}

	/**
	 * 
	 * @return
	 */
	public String getFolderPath() {
		return getFolderPath("");
	}

	/**
	 * 
	 * @param pathName
	 * @return
	 */
	public String getFolderPath(String pathName) {

		javax.swing.JFileChooser arq;
		arq = new javax.swing.JFileChooser();
		arq.setCurrentDirectory(new java.io.File(xableirosHome));
		arq.setDialogTitle("Selecione O Diretório " + pathName);
		this.setMessage("Selecionando Diretorio "+ pathName);
		arq.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);
		arq.setAcceptAllFileFilterUsed(false);

		int retorno = arq.showOpenDialog(null);

		if (retorno == javax.swing.JFileChooser.APPROVE_OPTION) {
			return arq.getSelectedFile().getAbsolutePath();
		} else if (retorno == javax.swing.JFileChooser.CANCEL_OPTION) {
			setMessage("Diretório Ñ Selecionado");

		} else {
			JOptionPane.showMessageDialog(null, "Fail to select directory");
			setMessage("Erro Ao Selecionar O Diretório" + pathName, "ERRO");
			return null;

		}
		
		return null;

	}

	/**
	 * 
	 * @param dsDir
	 */
	public void getDsFiles(String dsDir) {
		try {
			dsDir = this.pathDsText()+"/"+dsDir+"/";
			if (janela.comumCheckBox.isSelected()) {
				if (FuFile.isArq(dsDir+"sigacomum-ds.xml")) {
					String comum_ds = FuFile.readFileToString(dsDir+"sigacomum-ds.xml");
					janela.panel01TextArea.setText(comum_ds);
				} else {
					janela.panel01TextArea.setText("NÃO ENCONRTADO");
				}
			} else {
				janela.panel01TextArea.setText("");
			}

			if (janela.contratoCheckBox.isSelected()) {
				if (FuFile.isArq(dsDir+"sigacontrato-ds.xml")) {
					String comum_ds = FuFile.readFileToString(dsDir+"sigacontrato-ds.xml");
					janela.panel02TextArea.setText(comum_ds);
				} else {
					janela.panel02TextArea.setText("NÃO ENCONRTADO");
				}
			} else {
				janela.panel02TextArea.setText("");
			}

			if (janela.patrimonioCheckBox.isSelected()) {
				if (FuFile.isArq(dsDir+"sigapatrimonio-ds.xml")) {
					String comum_ds = FuFile.readFileToString(dsDir+"sigapatrimonio-ds.xml");
					janela.panel03TextArea.setText(comum_ds);
				} else {
					janela.panel03TextArea.setText("NÃO ENCONRTADO");
				}
			} else {
				janela.panel03TextArea.setText("");
			}

			if ((janela.panel01TextArea.getText().equals("NÃO ENCONRTADO") || janela.panel01TextArea.getText().equals("NÃO ENCONRTADO"))
				&& janela.panel02TextArea.getText().equals("NÃO ENCONRTADO") || janela.panel01TextArea.getText().equals("NÃO ENCONRTADO")
				&& (janela.panel03TextArea.getText().equals("NÃO ENCONRTADO") || janela.panel01TextArea.getText().equals("NÃO ENCONRTADO"))) {
					setMessage("Nenhum Arquivo Encontrado", "WARNING");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro Ao Copiar DS");
		}
	}

	/**
	 * 
	 * @param dsDir
	 * @return
	 */
	public Boolean copyDsFiles (String dsDir) {
		if (this.pathDsText().equals("")) {
			this.setMessage("Erro Ao Copiar, Diretório DS Ñ Encontrado", "ERRO");
			return false;
		}
		try {
			dsDir = this.pathDsText()+"/"+dsDir+"/";
			if (!FuFile.isDir(janela.pathDeployText.getText())) {
				this.setMessage("Erro Ao Copiar, Diretório Deploy Ñ Encontrado", "ERRO");
				return false;
			}

			if (janela.comumCheckBox.isSelected()) {
				if (!FileManipulator.copyFile(dsDir+"sigacomum-ds.xml",  janela.pathDeployText.getText()+"/sigacomum-ds.xml")) {
					this.setMessage("Erro Desconhecido Ao Copiar", "ERRO");
					return false;
				}
			}
			if (janela.contratoCheckBox.isSelected()) {
				if (!FileManipulator.copyFile(dsDir+"sigacontrato-ds.xml",  janela.pathDeployText.getText()+"/sigacontrato-ds.xml")) {
					this.setMessage("Erro Desconhecido Ao Copiar", "ERRO");
					return false;
				}
			}
			if (janela.patrimonioCheckBox.isSelected()) {
				if (!FileManipulator.copyFile(dsDir+"sigapatrimonio-ds.xml",  janela.pathDeployText.getText()+"/sigapatrimonio-ds.xml")) {
					this.setMessage("Erro Desconhecido Ao Copiar", "ERRO");
					return false;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			this.setMessage("Erro Desconhecido Ao Copiar", "ERRO");
			return false;
		}


		this.setMessage("Arquivos Copiados", "SUCCESS");
		return true;

	}

	/**
	 * 
	 * @param dsName
	 */
	public void saveDs(String dsName) {
		if (this.pathDsText().equals("")) {
			this.setMessage("Erro Ao Copiar, Diretório DS Ñ Encontrado", "ERRO");
			return;
		}

		String dsFullFilePath = this.pathDsText() + "/" + janela.dsPathList.getSelectedValue()+ "/" + dsName + ".xml";

		if (dsName.equalsIgnoreCase("sigacomum-ds")) {
			FuFile.saveFileFromString(dsFullFilePath, janela.panel01TextArea.getText());
			return;

		}
		if (dsName.equalsIgnoreCase("sigacontrato-ds")) {
			FuFile.saveFileFromString(dsFullFilePath, janela.panel02TextArea.getText());
			return;

		}
		if (dsName.equalsIgnoreCase("sigapatrimonio-ds")) {
			FuFile.saveFileFromString(dsFullFilePath, janela.panel03TextArea.getText());
			return;

		}

	}

	/**
	 * 
	 * @param idsContratos
	 */
	public void generateDeleteScriptsContratos (String idsContratos) {
		if (idsContratos==null || idsContratos.isEmpty()) {
			setMessage("Nenhum Id Inforado", "WARNING");
			return;
		}

		if(idsContratos.indexOf(",")<=0) {
			generateDeleteScriptContrato(idsContratos);
			return;
		}

		String[] idsContratosSplit = idsContratos.split(",");
		for (String idContrato : idsContratosSplit) {
			generateDeleteScriptContrato(idContrato);
		}

	}

	/**
	 * 
	 * @param idContrato
	 */
	public void generateDeleteScriptContrato (String idContrato) {
		String scriptName = janela.mssqlRadioButton.isSelected() ? "msSql" 
			: janela.oracleRadioButton.isSelected() ? "oracle"	
			: "postgres";			
		scriptName="exclui-contrato-"+scriptName;

		if (!FuFile.isArq(systemHome+"/conf/"+scriptName+".sql")) {
			setMessage("Arquivo Origem Do Script Não Encontrado", "WARNING");
			return;

		}

		ArrayList<String> deleteContrato = FileManipulator.buffReadAndReplaceFileToArrayList(systemHome+"/conf/"+scriptName+".sql", "idContratoSubstituir", idContrato);
		if (deleteContrato==null) {
			setMessage("Erro Ao Gerar Script De Exclusão!", "ERRO");
		}

		try {

			String scriptPath = systemHome.substring(0, systemHome.lastIndexOf("\\"));
			scriptPath+="/scripts/";

			Files.createDirectories(Paths.get(scriptPath));
			FileManipulator.buffWriteArrayList(scriptPath+scriptName+"-"+idContrato+".sql", deleteContrato);

			setMessage("Script Gerado Na Pasta 'scripts'", "SUCCESS");
			
		} catch (IOException e) {
			setMessage("Erro Ao Gerar Arquivo De Exclusão!", "ERRO");			
		}

	}

	/**
	 * 
	 * @param idsContratos
	 */
	public void generateScriptsContratos (String idsContratos, String souceFile) {
		if (idsContratos==null || idsContratos.isEmpty()) {
			setMessage("Nenhum Id Inforado", "WARNING");
			return;
		}
		souceFile+="-";
		if(idsContratos.indexOf(",")<=0) {
			generateScriptContrato(idsContratos, souceFile);
			return;
		}

		String[] idsContratosSplit = idsContratos.split(",");
		for (String idContrato : idsContratosSplit) {
			generateScriptContrato(idContrato, souceFile);
		}

	}

	/**
	 * 
	 * @param idContrato
	 */
	public void generateScriptContrato (String idContrato, String souceFile) {
		String scriptName = janela.mssqlRadioButton.isSelected() ? "msSql" 
			: janela.oracleRadioButton.isSelected() ? "oracle"	
			: "postgres";			
		scriptName=souceFile+scriptName;

		if (!FuFile.isArq(systemHome+"/conf/"+scriptName+".sql")) {
			setMessage("Arquivo Origem Do Script Não Encontrado", "WARNING");
			return;

		}

		ArrayList<String> deleteContrato = FileManipulator.buffReadAndReplaceFileToArrayList(systemHome+"/conf/"+scriptName+".sql", "idContratoSubstituir", idContrato);
		if (deleteContrato==null) {
			setMessage("Erro Ao Gerar Script De Exclusão!", "ERRO");
		}

		try {

			String scriptPath = systemHome.substring(0, systemHome.lastIndexOf("\\"));
			scriptPath+="/scripts/";

			Files.createDirectories(Paths.get(scriptPath));
			FileManipulator.buffWriteArrayList(scriptPath+scriptName+"-"+idContrato+".sql", deleteContrato);

			setMessage("Script Gerado Na Pasta 'scripts'", "SUCCESS");
			
		} catch (IOException e) {
			setMessage("Erro Ao Gerar Arquivo De Exclusão!", "ERRO");			
		}

	}

}
