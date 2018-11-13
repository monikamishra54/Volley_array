package com.example.mgc.volley_array;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by mgc on 7/10/2017.
 */
public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {
private List<Movie> moviesList;
    Details_Activity c;

    public MoviesAdapter(Details_Activity c, List<Movie> moviesList){
        this.moviesList=moviesList;
        this.c=c;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row,parent,false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Movie movie=moviesList.get(position);

        holder.title.setText(movie.getTitle());
        holder.gener.setText(movie.getGenre());
        holder.year.setText(movie.getYear());
    }

    @Override
    public int getItemCount() {

        return moviesList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView title,year,gener;
        public MyViewHolder(View View) {
            super(View);

           title=(TextView) View.findViewById(R.id.title);
            year=(TextView) View.findViewById(R.id.year);
            gener=(TextView) View.findViewById(R.id.genre);

            View.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            Toast.makeText(c,getAdapterPosition()+"",Toast.LENGTH_SHORT).show();
            String value=title.getText().toString();

            Toast.makeText(c,value,Toast.LENGTH_SHORT).show();
        }
    }
}
