package application.logica;


import static net.sf.dynamicreports.report.builder.DynamicReports.*;

import net.sf.dynamicreports.adhoc.AdhocManager;
import net.sf.dynamicreports.adhoc.configuration.AdhocColumn;
import net.sf.dynamicreports.adhoc.configuration.AdhocConfiguration;
import net.sf.dynamicreports.adhoc.configuration.AdhocReport;
import net.sf.dynamicreports.adhoc.report.DefaultAdhocReportCustomizer;
import net.sf.dynamicreports.examples.Templates;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.ReportBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.group.ColumnGroupBuilder;
import net.sf.dynamicreports.report.constant.GroupHeaderLayout;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;

public class ReportGenerator {

	public ReportGenerator() {
		build();
	}

	/*private void build() {
		AdhocConfiguration configuration = new AdhocConfiguration();

		AdhocReport report = new AdhocReport();

		configuration.setReport(report);

		AdhocColumn columnaI = new AdhocColumn();
		columnaI.setName("Resultados");
		report.addColumn(columnaI);
		
		AdhocColumn columnaQ = new AdhocColumn();
		columnaQ.setName("quantity");
		report.addColumn(columnaQ);

		try {

			JasperReportBuilder reportBuilder = AdhocManager.createReport(configuration.getReport(),
					new ReportCustomizer());

			reportBuilder.setDataSource(createDataSource());

			reportBuilder.show();

		} catch (DRException e) {

			e.printStackTrace();

		}

	}*/

	private void build(){
		TextColumnBuilder<String> itemColumn = col.column("", "item", type.stringType());
		
		ColumnGroupBuilder itemGroup = grp.group(itemColumn)	
         .setTitleWidth(30)
         .setHeaderLayout(GroupHeaderLayout.TITLE_AND_VALUE)
         .showColumnHeaderAndFooter();

		try{
			report()
			.setTemplate(Templates.reportTemplate)
			.setShowColumnTitle(false)			 
			.columns(
					col.column("Texto", "Recomendaciones", type.stringType()))
			.groupBy(itemGroup)						 
			.title(Templates.createTitleComponent("Informe de Resultados"))						 
			.pageFooter(Templates.footerComponent)							 
			.setDataSource(createDataSource())	
			.show(false);
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	
	
	private JRDataSource createDataSource() {
		DRDataSource dataSource = new DRDataSource("item","Recomendaciones");
		Test test = Test.getReference();
		for(Pregunta p: test.getListaPreguntas()){
			if(!p.isCorrect()){
				dataSource.add(p.getGrupo(),p.getRecomendacion());
			}	
		}
		return dataSource;
	}

	private class ReportCustomizer extends DefaultAdhocReportCustomizer {

		public void customize(ReportBuilder<?> report, AdhocReport adhocReport) throws DRException {

			super.customize(report, adhocReport);
			// default report values
			report.setTemplate(Templates.reportTemplate);
			report.title(Templates.createTitleComponent("Resultado"));
			// a fixed page footer that user cannot change, this customization
			// is not stored in the xml file
			report.pageFooter(Templates.footerComponent);

		}

	}
	
}
