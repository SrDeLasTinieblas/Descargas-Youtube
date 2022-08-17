from pytube import YouTube

def main(filter, Url, destino):
    try:
        yt = YouTube(Url)
        fileName = yt.streams.get_highest_resolution().default_filename
        if filter == ".mp3":
            try:
                yt = YouTube(Url)
                t = yt.streams.filter(only_audio=True).all()
                audio = fileName.replace('.mp4', '.mp3')
                t[0].download("/storage/emulated/0/"+destino, filename= audio)
                return "Descarga completa : "+ audio
            except NameError:
                return "error => " + NameError

#---------------------------------------------------------------------------------------------------------------------------    
        elif filter == ".mp4":
            try:
                yt = YouTube(Url)
                video = yt.streams.get_highest_resolution()
                video.download("/storage/emulated/0/"+destino)   
                return "Descarga completa : "+ fileName
            except NameError:
                return "error => " + NameError
    except NameError:
        return "Error ==>" + NameError

