package com.apps.diogo.timetobusufrn.Classes.Adapters.Maps;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.apps.diogo.timetobusufrn.Classes.Adapters.Timeline.comentExpandable;
import com.apps.diogo.timetobusufrn.Classes.Modelos.Post;
import com.apps.diogo.timetobusufrn.Classes.Modelos.Usuario;
import com.apps.diogo.timetobusufrn.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Diogo on 19/10/2017.
 */

public class InfoWindowListAdapter extends ArrayAdapter<Post>
{
    private final Context context;
    ArrayList<Post> posts = new ArrayList<Post>();
    
    public InfoWindowListAdapter(Context context, ArrayList<Post> posts)
    {
        super(context, R.layout.postlayout, posts);
        this.context = context;
        this.posts = posts;
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        
        final View rowView = inflater.inflate(R.layout.item_custom_info_window, parent, false);
    
        CircleImageView imagemUsr = (CircleImageView) rowView.findViewById(R.id.imageUser);
        TextView textUser = (TextView) rowView.findViewById(R.id.tituloMap);
        TextView textDados = (TextView) rowView.findViewById(R.id.dadosMap);
        
        Post post = posts.get(position);
        
        textUser.setText( post.getUsuario().getNome() );
        textDados.setText( post.toString() );
        
        if( post.getUsuario().getFoto() != null )
        {
            //Toast.makeText(context, "Achou imagem no banco !", Toast.LENGTH_SHORT).show();
            
            byte[] fotoArray = post.getUsuario().getFoto();
            
            Bitmap raw  = BitmapFactory.decodeByteArray(fotoArray,0,fotoArray.length);
    
            imagemUsr.setImageBitmap(raw);
        }
        
        return rowView;
    }
    
    private void setImagemEmpresa(int idEmpresa, ImageView imageOnibus)
    {
        String imageName;
        
        switch ( idEmpresa )
        {
            case 0:
                imageName = "guanabaralow";
                break;
            
            case 1:
                imageName = "viasullow";
                break;
            
            case 2:
                imageName = "conceicaolow";
                break;
            
            case 3:
                imageName = "cidadedonatallow";
                break;
            
            case 4:
                imageName = "reunidaslow";
                break;
            
            case 5:
                imageName = "santamarialow";
                break;
            
            default:
                imageName = "guanabaralow";
                break;
        }
        
        int resID = context.getResources().getIdentifier(imageName , "drawable", context.getPackageName());
        imageOnibus.setImageResource(resID);
    }
    
    public static boolean buscaImagemPerfil( ImageView ibageView, Context contexto, Usuario usuario )
    {
        String fileName = "thumb" + usuario.getMatricula();
        String local = contexto.getFilesDir().getPath()+ "/" + fileName + ".jpg";
        
        try
        {
            File f = new File(local);
            
            if( f.exists() )
            {
                Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
                ibageView.setImageBitmap(b);
                return true;
            }
            else
                return false;
            
        }
        catch (FileNotFoundException e)
        {
            //e.printStackTrace();
            return false;
        }
    }
}
