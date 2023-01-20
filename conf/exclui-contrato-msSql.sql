DELETE FROM TB_CONTRATO_DOTACAO WHERE CONT_ID = idContratoSubstituir;
DELETE FROM TB_PCDFI_PRE_EMPENHO WHERE PE_ID IN(SELECT PE_ID FROM TB_PRE_EMPENHO WHERE CONT_ID = idContratoSubstituir);
DELETE FROM TB_SOLICITACAO_PRE_EMPENHO WHERE CONT_ID = idContratoSubstituir;
DELETE FROM TB_PRE_EMPENHO WHERE CONT_ID = idContratoSubstituir;
DELETE PCDFI FROM TB_PARC_CONT_DOT_FORN_ITEM PCDFI
     JOIN TB_PARC_CONT_DOT_ORC PCDO ON PCDO.PARC_CONT_DOT_ORC_ID = PCDFI.PARC_CONT_DOT_ORC_ID
     JOIN TB_PARCELA_CONTRATO PC ON PC.PC_ID = PCDO.PC_ID
WHERE PC.CONT_ID = idContratoSubstituir;
DELETE FROM TB_PARC_CONT_DOT_ORC WHERE PC_ID IN (SELECT PC_ID FROM TB_PARCELA_CONTRATO WHERE CONT_ID = idContratoSubstituir);
DELETE FROM TB_PARC_CONT_EMP WHERE EM_ID IN (SELECT EM_ID FROM TB_EMPENHO WHERE FK_EM_CONT = idContratoSubstituir);
DELETE FROM TB_ITEM_DOC_EMPENHO WHERE EM_ID IN (SELECT EM_ID FROM TB_EMPENHO WHERE FK_EM_CONT = idContratoSubstituir);
DELETE FROM TB_ITEM_DOC_FISCAL WHERE DF_ID IN (SELECT DF_ID FROM TB_DOC_FISCAL WHERE CONT_ID = idContratoSubstituir);
DELETE FROM TB_DOC_FINANCEIRO WHERE DFIN_ID_ORIGINAL IN (SELECT DFIN_ID FROM TB_DOC_FINANCEIRO WHERE CONT_ID = idContratoSubstituir);
DELETE FROM TB_DOC_FINANCEIRO WHERE CONT_ID = idContratoSubstituir;
DELETE FROM TB_DOC_FISCAL WHERE CONT_ID = idContratoSubstituir;
DELETE FROM AUD_TB_PARCELA_CONTRATO WHERE CONT_ID = idContratoSubstituir;
DELETE FROM AUD_TB_ITEM_MEDICAO WHERE MD_ID IN (SELECT MD_ID FROM TB_MEDICAO WHERE CONT_ID = idContratoSubstituir);
DELETE FROM AUD_TB_ITEM_CONTRATO WHERE FK_IC_CONT = idContratoSubstituir;
DELETE FROM AUD_TB_MEDICAO WHERE CONT_ID = idContratoSubstituir;
DELETE FROM AUD_TB_CONT WHERE CONT_ID = idContratoSubstituir;
DELETE FROM AUD_TB_FORNECEDOR_CONTRATO WHERE CONT_ID = idContratoSubstituir;
DELETE FROM TB_PARCELA_CONTRATO WHERE CONT_ID = idContratoSubstituir;
DELETE FROM TB_ANEXO_CONTRATO WHERE CONT_ID = idContratoSubstituir;
DELETE CP FROM TB_COMISSAO_PERMISSOES CP
     JOIN TB_COMISSAO C ON (C.CMS_ID = CP.FK_CMSP_COMISSAO)
WHERE C.FK_CMS_CONT = idContratoSubstituir;
DELETE FROM TB_COMISSAO WHERE FK_CMS_CONT = idContratoSubstituir;
DELETE FROM TB_REVISAO WHERE CONT_ID = idContratoSubstituir;
DELETE FROM TB_PARECER WHERE FK_PAR_CONT =idContratoSubstituir;
DELETE FROM TB_REFORCO_EMPENHO WHERE EM_ID IN (SELECT EM_ID FROM TB_EMPENHO WHERE FK_EM_CONT = idContratoSubstituir);
DELETE FROM TB_ESTORNO_EMPENHO WHERE EM_ID IN (SELECT EM_ID FROM TB_EMPENHO WHERE FK_EM_CONT = idContratoSubstituir);
DELETE FROM TB_EMPENHO WHERE FK_EM_CONT = idContratoSubstituir;
DELETE FROM TB_ITEM_MEDICAO WHERE MD_ID IN (SELECT MD_ID FROM TB_MEDICAO WHERE CONT_ID = idContratoSubstituir);
DELETE FROM TB_ITEM_CONTRATO WHERE FK_IC_CONT = idContratoSubstituir;
DELETE FROM TB_FORNECEDOR_CONTRATO WHERE CONT_ID = idContratoSubstituir;
DELETE FROM TB_MEDICAO WHERE CONT_ID = idContratoSubstituir;
DELETE FROM TB_CLAUSULA_CONTRATO WHERE MC_ID IN (SELECT MC_ID FROM TB_MINUTA_CONTRATO WHERE CONT_ID = idContratoSubstituir);
DELETE FROM TB_MINUTA_CONTRATO WHERE CONT_ID = idContratoSubstituir;
DELETE FROM TB_INTERVENIENTE_FINANCEIRO  WHERE CONT_ID = idContratoSubstituir;
DELETE FROM TB_ASSINATURA WHERE FK_ASS_CONT = idContratoSubstituir;
DELETE FROM TB_ORDEM_SERVICO WHERE FK_OS_CONT = idContratoSubstituir;
DELETE FROM TB_CELEBRACAO WHERE FK_CLB_CONT = idContratoSubstituir;
DELETE FROM TB_DOC_EXTRATO WHERE CONT_ID = idContratoSubstituir;
DELETE FROM TB_PUBLICACAO WHERE CONT_ID = idContratoSubstituir;
DELETE FROM TB_EVENTO WHERE CONT_ID = idContratoSubstituir;
DELETE FROM TB_REGISTRO_OCORRENCIA  WHERE CONT_ID = idContratoSubstituir;
DELETE FROM TB_ESTORNO_SALDO_CONT WHERE CONT_ID = idContratoSubstituir;
DELETE FROM TB_MENSAGEM WHERE CONT_ID = idContratoSubstituir;
DELETE FROM TB_AJUSTE WHERE CONT_ID = idContratoSubstituir;
DELETE FROM TB_CONTRATO_INCONSISTENCIA WHERE CONT_ID = idContratoSubstituir;
DELETE FROM TB_USUARIO_NOTIFICACAO WHERE nt_id in (SELECT n.nt_id FROM TB_NOTIFICACAO n WHERE n.cont_id = idContratoSubstituir);
DELETE FROM TB_REFERENCIA_NOTIFICACAO_DADO WHERE nt_id in (SELECT n.nt_id FROM TB_NOTIFICACAO n WHERE n.cont_id = idContratoSubstituir);
DELETE FROM TB_NOTIFICACAO WHERE CONT_ID = idContratoSubstituir;
DELETE FROM TB_HISTORICO_MODIFICACAO WHERE CONT_ID = idContratoSubstituir;
DELETE FROM TB_DETALHE_DOTACAO WHERE DDT_ID IN (SELECT DD.DDT_ID FROM TB_DETALHE_DETALHAMENTO DD WHERE DD.CONT_ID = idContratoSubstituir);
DELETE FROM TB_DETALHE_DETALHAMENTO WHERE CONT_ID = idContratoSubstituir;
DELETE FROM TB_DADOS_PROCESSO WHERE CONT_ID = idContratoSubstituir;
DELETE FROM TB_CONT WHERE CONT_ID = idContratoSubstituir;