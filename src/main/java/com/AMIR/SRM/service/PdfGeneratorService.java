package com.AMIR.SRM.service;

import com.AMIR.SRM.domain.Order;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class PdfGeneratorService {
    public void export(HttpServletResponse response, Order order) throws IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        BaseFont bfTNR = BaseFont.createFont("C://Windows//Fonts//Arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font fontTitle = new Font(bfTNR, 18);

        Paragraph title = new Paragraph("Договор №" + order.getId(), fontTitle);
        title.setAlignment(Paragraph.ALIGN_CENTER);

        Font fontParagraph = new Font(bfTNR, 14);

        Paragraph[] paragraphs = new Paragraph[8];
        paragraphs[0] = new Paragraph("Номер заказа: " + order.getId(), fontParagraph);
        paragraphs[1] = new Paragraph("Исполнитель: " + order.getProvider(), fontParagraph);
        paragraphs[2] = new Paragraph("Наименование товара: " + order.getProduct_name(), fontParagraph);
        paragraphs[3] = new Paragraph("Краткое описание: " + order.getDescription(), fontParagraph);
        paragraphs[4] = new Paragraph("Дата доставки: " + order.getReal_date(), fontParagraph);
        paragraphs[5] = new Paragraph("Цена одной единицы товара: " + order.getReal_price() + " руб", fontParagraph);
        paragraphs[6] = new Paragraph("Количество: " + order.getCount(), fontParagraph);
        paragraphs[7] = new Paragraph("Итого к оплате: " + order.getReal_price()*order.getCount() + " руб", fontParagraph);



        document.add(title);

        for (int i = 0; i< paragraphs.length; i++) {
            paragraphs[i].setAlignment(Paragraph.ALIGN_LEFT);
            document.add(paragraphs[i]);
        }
        document.close();
    }
}
