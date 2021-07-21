package com.example.mytoolbar2;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

//создадим адаптер для создания представления, отображаемые в  RecyclerView,
//и связать каждое представление с соответствующими данными
public class CaptionedImagesAdapter extends
        RecyclerView.Adapter<CaptionedImagesAdapter.ViewHolder> {
    //для этого мы расширяем класс RecyclerView.Adapter и переопределяем его различные методы

    private String[] captions; //переменные для хнанения строк и id
    private int[] imageIds;
    private Listener listener; //добавим объект listener

    //реализуем интерфейс
    interface Listener {
        void onClick(int position);
    }

    //сообщает адаптеру, какие представления должны использоваться для элементов данных
    public static class ViewHolder extends RecyclerView.ViewHolder {

        //указываем, что данный класс использует карточное представление
        private CardView cardView;

        public ViewHolder(CardView v) {
            super(v); //суперкласс включает метаданные, необходимые для правильной работы адаптера
            cardView = v;
        }

    }

    //создаем конструктор для передачи данных
    public CaptionedImagesAdapter(String[] captions, int[] imageIds) {
        this.captions = captions;
        this.imageIds = imageIds;
    }

    //метод вызывается, когда  RecyclerView требуется создать ViewHolder
    @Override
    public CaptionedImagesAdapter.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        //Код создания экземпляра ViewHolder
        CardView cv = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_captioned_image, parent, false);
        //Получает объект LayoutInflater,к. преобразует макет в CardView
        return new ViewHolder(cv);
    }

    //возвращает к-во элементов данных
    @Override
    public int getItemCount() {
        return captions.length; //длина массива равна к-ву элементов данных в RecyclerView
    }

    //aктивности и фрагменты используют этот метод для регистрации себя в качестве слушателя
    public void setListener(Listener listener) {
        this.listener = listener;
    }

    //RecyclerView вызывает этот метод, когда потребуется использовать (или повторно использовать)
    // ViewHolder для новой порции данных.
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        //ременную position необходимо снабдить модификатором final,
        //так как она используется во внутреннем классе
        CardView cardView = holder.cardView;
        ImageView imageView = (ImageView) cardView.findViewById(R.id.info_image);
        Drawable drawable =
                ContextCompat.getDrawable(cardView.getContext(), imageIds[position]);
        imageView.setImageDrawable(drawable);
        imageView.setContentDescription(captions[position]);
        TextView textView = (TextView) cardView.findViewById(R.id.info_text);
        textView.setText(captions[position]);
        //Интерфейс добавляетс к CardView.
        cardView.setOnClickListener(new View.OnClickListener() { //При щелчке на CardView вызвать ме
            // тод onClick() интерфейса Listener
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(position);
                }
            }
        });
    }

}


