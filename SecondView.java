package com.cgi.support.views;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;


import com.vaadin.addon.spreadsheet.Spreadsheet;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.colorpicker.Color;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ColorPicker;
import com.vaadin.ui.Composite;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class SecondView extends VerticalLayout implements View{
	public static final String NAME="Second View";
	private Spreadsheet spreadsheet = null;
	
	public SecondView()
	{
		setSizeFull();
	      

        HorizontalLayout styleToolbar = createStyleToolbar();
        addComponent(styleToolbar);
        setExpandRatio(styleToolbar, 0);
        initSpreadsheet();
        addComponent(spreadsheet);
        setExpandRatio(spreadsheet, 1);
	}
	
	
	
	private void initSpreadsheet(){
		   File sampleFile = new File("F:\\vaadin_workspace\\NavigationDemo\\src\\main\\resources\\JavaJ2EE_3Days.xlsx");
		   try {
		       spreadsheet = new Spreadsheet(sampleFile);
		       
		       spreadsheet.setWidth("800px");
		       spreadsheet.setHeight("500px");
		   } catch (IOException e) {
		       e.printStackTrace();
		   }
		}
	

    private HorizontalLayout createStyleToolbar() {
        HorizontalLayout toolbar = new HorizontalLayout();
        Button boldButton = new Button(FontAwesome.BOLD);
        boldButton.addClickListener(new ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                updateSelectedCellsBold();
            }
        });
        ColorPicker backgroundColor = new ColorPicker();
        backgroundColor.setCaption("Background Color");
        backgroundColor.addValueChangeListener(
                event -> updateSelectedCellsBackgroundColor(event.getValue()));
        ColorPicker fontColor = new ColorPicker();
        fontColor.setCaption("Font Color");
        fontColor.addValueChangeListener(
                event -> updateSelectedCellsFontColor(event.getValue()));

        toolbar.addComponent(boldButton);
        toolbar.addComponent(backgroundColor);
        toolbar.addComponent(fontColor);
        return toolbar;
    }

    private void updateSelectedCellsBold() {
        if (spreadsheet != null) {
            List<Cell> cellsToRefresh = new ArrayList<>();
            for (CellReference cellRef : spreadsheet
                    .getSelectedCellReferences()) {
                // Obtain Cell using CellReference
                Cell cell = getOrCreateCell(cellRef);
                // Clone Cell CellStyle
                CellStyle style = cloneStyle(cell);
                // Clone CellStyle Font
                Font font = cloneFont(style);
                // Toggle current bold state
                font.setBold(!font.getBold());
                style.setFont(font);
                cell.setCellStyle(style);

                cellsToRefresh.add(cell);
            }
            // Update all edited cells
            spreadsheet.refreshCells(cellsToRefresh);
        }
    }

    private void updateSelectedCellsBackgroundColor(Color newColor) {
        if (spreadsheet != null && newColor != null) {
            List<Cell> cellsToRefresh = new ArrayList<>();
            for (CellReference cellRef : spreadsheet
                    .getSelectedCellReferences()) {
                // Obtain Cell using CellReference
                Cell cell = getOrCreateCell(cellRef);
                // Clone Cell CellStyle
                // This cast an only be done when using .xlsx files
                XSSFCellStyle style = (XSSFCellStyle) cloneStyle(cell);
                XSSFColor color = new XSSFColor(java.awt.Color.decode(newColor
                        .getCSS()));
                // Set new color value
                style.setFillForegroundColor(color);
                cell.setCellStyle(style);

                cellsToRefresh.add(cell);
            }
            // Update all edited cells
            spreadsheet.refreshCells(cellsToRefresh);
        }
    }

    private void updateSelectedCellsFontColor(Color newColor) {
        if (spreadsheet != null && newColor != null) {
            List<Cell> cellsToRefresh = new ArrayList<>();
            for (CellReference cellRef : spreadsheet
                    .getSelectedCellReferences()) {
                Cell cell = getOrCreateCell(cellRef);
                // Workbook workbook = spreadsheet.getWorkbook();
                XSSFCellStyle style = (XSSFCellStyle) cloneStyle(cell);
                XSSFColor color = new XSSFColor(java.awt.Color.decode(newColor
                        .getCSS()));
                XSSFFont font = (XSSFFont) cloneFont(style);
                font.setColor(color);
                style.setFont(font);
                cell.setCellStyle(style);
                cellsToRefresh.add(cell);
            }
            // Update all edited cells
            spreadsheet.refreshCells(cellsToRefresh);
        }
    }

    private Cell getOrCreateCell(CellReference cellRef) {
        Cell cell = spreadsheet.getCell(cellRef.getRow(), cellRef.getCol());
        if (cell == null) {
            cell = spreadsheet.createCell(cellRef.getRow(), cellRef.getCol(),
                    "");
        }
        return cell;
    }

    private CellStyle cloneStyle(Cell cell) {
        CellStyle newStyle = spreadsheet.getWorkbook().createCellStyle();
        newStyle.cloneStyleFrom(cell.getCellStyle());
        return newStyle;
    }

    private Font cloneFont(CellStyle cellstyle) {
        Font newFont = spreadsheet.getWorkbook().createFont();
        Font originalFont = spreadsheet.getWorkbook().getFontAt(
                cellstyle.getFontIndex());
        if (originalFont != null) {
            newFont.setBold(originalFont.getBold());
            newFont.setItalic(originalFont.getItalic());
            newFont.setFontHeight(originalFont.getFontHeight());
            newFont.setUnderline(originalFont.getUnderline());
            newFont.setStrikeout(originalFont.getStrikeout());
            // This cast an only be done when using .xlsx files
            XSSFFont originalXFont = (XSSFFont) originalFont;
            XSSFFont newXFont = (XSSFFont) newFont;
            newXFont.setColor(originalXFont.getXSSFColor());
        }
        return newFont;
    }

}
