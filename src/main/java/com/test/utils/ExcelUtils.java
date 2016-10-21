/*** Eclipse Class Decompiler plugin, copyright (c) 2012 Chao Chen (cnfree2000@hotmail.com) ***/
package com.test.utils;

import java.awt.Color;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
    public static final Integer HEADR_FONT_SIZE = Integer.valueOf(20);

    public static String left = "left";
    public static String right = "right";
    public static String center = "center";
    public static String top = "top";
    public static String bottom = "bottom";
    public static int allFontSize = 10;
    public static String headerFont = "微软雅黑";
    public static String dataFont = "Tahoma";

    public static CellStyle setStyle(Workbook wb) {
        CellStyle style = wb.createCellStyle();
        setFrame(style, true);
        setFont(style, wb.createFont(), headerFont, allFontSize, false);
        return style;
    }

    public static CellStyle setStyle_header(Workbook wb) {
        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        setFont(style, font, dataFont, 11, true);
        setFrame(style, true);
        setAlign(style, center, center, true);
        return style;
    }

    public static CellStyle setStyle_number(Workbook wb, String formatType) {
        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        DataFormat format = wb.createDataFormat();
        setFont(style, font, dataFont, 11, true);
        setFrame(style, true);
        setAlign(style, right, center, false);
        setFormat(style, format, formatType);
        return style;
    }

    public static CellStyle setStyle_date(Workbook wb, String formatType) {
        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        DataFormat format = wb.createDataFormat();
        setFont(style, font, dataFont, 11, true);
        setFrame(style, true);
        setAlign(style, center, center, false);
        setFormat(style, format, formatType);
        return style;
    }

    public static void setFont(CellStyle style, Font font, String fontName, int fontSize, boolean isOrNotOverstrike) {
        font.setFontName(fontName);
        font.setFontHeightInPoints((short) fontSize);
        if (isOrNotOverstrike) {
            font.setBoldweight((short) 700);
        }
        style.setFont(font);
    }

    public static void setFrame(CellStyle style, boolean isOrNotframe) {
        if (isOrNotframe) {
            style.setBorderBottom((short)1);
            style.setBorderLeft((short)1);
            style.setBorderTop((short)1);
            style.setBorderRight((short)1);
        }
    }

    public static void setAlign(CellStyle style, String crosswiseAlign, String lengthwiseAlign, boolean wrapText) {
        if ("right".equalsIgnoreCase(crosswiseAlign)) {
            style.setAlignment((short)3);
        } else if ("left".equalsIgnoreCase(crosswiseAlign)) {
            style.setAlignment((short)1);
        } else if ("center".equalsIgnoreCase(crosswiseAlign)) {
            style.setAlignment((short)2);
        }
        if ("top".equalsIgnoreCase(lengthwiseAlign))
            style.setVerticalAlignment((short)0);
        else if ("center".equalsIgnoreCase(lengthwiseAlign))
            style.setVerticalAlignment((short)1);
        else if ("bottom".equalsIgnoreCase(lengthwiseAlign)) {
            style.setVerticalAlignment((short)2);
        }
        if (wrapText)
            style.setWrapText(true);
    }

    public static void setFormat(CellStyle style, DataFormat format, String formatType) {
        style.setDataFormat(format.getFormat(formatType));
    }

    public static void setColor(XSSFWorkbook wb, int a, int b, int c) {
        Color color = new Color(a, b, c);
        XSSFCellStyle style = wb.createCellStyle();
        style.setFillForegroundColor(new XSSFColor(color));
        style.setFillPattern((short)1);
    }

    public static void setColor(XSSFWorkbook wb, String colorDecode) {
        Color color = new Color(0, 0, 0);
        Color.decode(colorDecode);
        XSSFCellStyle style = wb.createCellStyle();
        style.setFillForegroundColor(new XSSFColor(color));
        style.setFillPattern((short)1);
    }

    public static void addMergedRegion(Sheet sheet, String[] refs) {
        if ((refs != null) && (refs.length > 0))
            for (String ref : refs)
                sheet.addMergedRegion(CellRangeAddress.valueOf(ref));
    }

    public static void addMergedRegion(Sheet sheet, int firstRow, int lastRow, int firstCol, int lastCol) {
        sheet.addMergedRegion(
                CellRangeAddress.valueOf(CellReference.convertNumToColString(firstCol) + (firstRow + 1) + ":" + CellReference.convertNumToColString(lastCol) + (lastRow + 1)));
    }

    public static Color getDefaultHeaderColor() {
        return new Color(250, 192, 144);
    }

    public static CellStyle getHeaderCellStyle(Workbook wb, Integer fontSize) {
        if (fontSize == null) {
            fontSize = Integer.valueOf(allFontSize);
        }
        XSSFCellStyle firstRowStyle = (XSSFCellStyle) wb.createCellStyle();

        setFont(firstRowStyle, wb.createFont(), headerFont, fontSize.intValue(), false);

        firstRowStyle.setFillForegroundColor(new XSSFColor(getDefaultHeaderColor()));
        firstRowStyle.setFillPattern((short) 1);

        setAlign(firstRowStyle, "left", "left", false);

        setFrame(firstRowStyle, true);

        return firstRowStyle;
    }

    public static CellStyle getDefaultStringStyle(Workbook wb) {
        XSSFCellStyle stringCellStyle = (XSSFCellStyle) wb.createCellStyle();

        setFont(stringCellStyle, wb.createFont(), dataFont, allFontSize, false);

        setAlign(stringCellStyle, "left", "left", false);

        setFrame(stringCellStyle, true);

        return stringCellStyle;
    }

    public static CellStyle getDefaultDateStyle(Workbook wb, String formatType) {
        XSSFCellStyle dateCellStyle = (XSSFCellStyle) wb.createCellStyle();

        setFont(dateCellStyle, wb.createFont(), dataFont, allFontSize, false);

        DataFormat dayDateFormat = wb.createDataFormat();
        dateCellStyle.setDataFormat(dayDateFormat.getFormat(formatType));

        setAlign(dateCellStyle, "left", "left", false);

        setFrame(dateCellStyle, true);

        return dateCellStyle;
    }

    public static CellStyle getDefaultAmoutStyle(Workbook wb) {
        XSSFCellStyle AmtCellStyl = (XSSFCellStyle) wb.createCellStyle();

        setFont(AmtCellStyl, wb.createFont(), dataFont, allFontSize, false);

        DataFormat dayDateFormat = wb.createDataFormat();
        AmtCellStyl.setDataFormat(dayDateFormat.getFormat("0.0000"));

        setAlign(AmtCellStyl, "right", "right", false);

        setFrame(AmtCellStyl, true);

        return AmtCellStyl;
    }

    public static CellStyle getDefaultNumberStyle(Workbook wb) {
        XSSFCellStyle AmtCellStyl = (XSSFCellStyle) wb.createCellStyle();

        setFont(AmtCellStyl, wb.createFont(), dataFont, allFontSize, false);

        DataFormat dayDateFormat = wb.createDataFormat();
        AmtCellStyl.setDataFormat(dayDateFormat.getFormat("0.00"));

        setAlign(AmtCellStyl, "right", "right", false);

        setFrame(AmtCellStyl, true);

        return AmtCellStyl;
    }

    public static CellStyle getDefaultIntegerStyle(Workbook wb) {
        XSSFCellStyle AmtCellStyl = (XSSFCellStyle) wb.createCellStyle();

        setFont(AmtCellStyl, wb.createFont(), dataFont, allFontSize, false);

        DataFormat dayDateFormat = wb.createDataFormat();
        AmtCellStyl.setDataFormat(dayDateFormat.getFormat("0"));

        setAlign(AmtCellStyl, "right", "right", false);

        setFrame(AmtCellStyl, true);

        return AmtCellStyl;
    }
}